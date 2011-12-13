package com.murex.ccportal.util;

import com.google.inject.Injector;
import com.mvp4g.client.annotation.Event;
import com.mvp4g.client.annotation.InitHistory;
import com.mvp4g.client.event.EventBus;
import com.mvp4g.client.event.EventHandlerInterface;
import com.mvp4g.client.presenter.PresenterInterface;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hsuleiman
 *         Date: 12/13/11
 *         Time: 9:32 AM
 */
public class ServerEventBus <T extends EventBus> {

  Class<T> interfaceClass;

  T eventBus;
  @SuppressWarnings("unchecked")
  Map<Class<PresenterInterface>, PresenterInterface> presenters = new HashMap<Class<PresenterInterface>, PresenterInterface>();
  Map<String, EventDescriptor> events = new HashMap<String, EventDescriptor>();
  EventDescriptor initEvent;

  Injector injector;

  @SuppressWarnings("unchecked")
  public ServerEventBus(Class<T> interfaceClass, Injector injector) {
    this.injector = injector;
    this.interfaceClass = interfaceClass;
    eventBus = (T) Proxy.newProxyInstance(EventBus.class.getClassLoader(),
        new Class[]{interfaceClass}, new EventBusInvocationHandler());
    for (Method method : interfaceClass.getMethods())
      if (method.isAnnotationPresent(Event.class)) {
        Event event = method.getAnnotation(Event.class);
        EventDescriptor eventDescriptor = new EventDescriptor();
        eventDescriptor.method = method;
        eventDescriptor.eventName = method.getName();
        eventDescriptor.targetMethodName = "on" + Character.toUpperCase(eventDescriptor.eventName.charAt(0)) + eventDescriptor.eventName.substring(1);
        for (Class<? extends EventHandlerInterface> cls : event.handlers())
          eventDescriptor.handlers.add(getPresenter(cls));
        events.put(eventDescriptor.eventName, eventDescriptor);
        if (method.isAnnotationPresent(InitHistory.class))
          initEvent = eventDescriptor;
      }
  }

  @SuppressWarnings("unchecked")
  public void bindView(Class<? extends PresenterInterface> presenterClass, Object view) {
    PresenterInterface presenter = getPresenter(presenterClass);
    presenter.setView(view);
    presenter.setEventBus(eventBus);
    presenter.bind();
  }

  public void init() {
    try {
      initEvent.method.invoke(eventBus);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @SuppressWarnings("unchecked")
  public <T extends EventHandlerInterface> T getPresenter(Class<T> presenterClass) {
    T presenter = (T) presenters.get(presenterClass);
    if (presenter == null) {
      try {
        presenter = injector.getInstance(presenterClass);
        presenters.put((Class<PresenterInterface>) presenterClass, (PresenterInterface) presenter);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
    return presenter;
  }

  public T getEventBus() {
    return eventBus;
  }

  class EventDescriptor {
    List<EventHandlerInterface> handlers = new ArrayList<EventHandlerInterface>();
    String eventName;
    String targetMethodName;
    Method method;
  }

  class EventBusInvocationHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      if ("hashCode".equals(method.getName())) {
        return ServerEventBus.this.hashCode();
      }
      EventDescriptor eventDescriptor = events.get(method.getName());
      for (EventHandlerInterface presenter : eventDescriptor.handlers) {
        Method presenterMethod = presenter.getClass().getMethod(eventDescriptor.targetMethodName, eventDescriptor.method.getParameterTypes());
        presenterMethod.invoke(presenter, args);
      }
      return null;
    }
  }
}
