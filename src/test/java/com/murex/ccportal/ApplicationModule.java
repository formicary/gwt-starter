package com.murex.ccportal;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.murex.ccportal.client.CCEventBus;
import com.murex.ccportal.util.ServerEventBus;
import com.mvp4g.client.annotation.Presenter;
import org.reflections.Reflections;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

/**
 * @author hsuleiman
 *         Date: 12/13/11
 *         Time: 9:39 AM
 */
public class ApplicationModule extends AbstractModule {
  @Override
  protected void configure() {
    Reflections reflections = new Reflections(new ConfigurationBuilder()
        .addUrls(ClasspathHelper.forPackage("com.murex.ccportal"))
        .setScanners(new TypeAnnotationsScanner())
    );
    for (Class<?> cls : reflections.getTypesAnnotatedWith(Presenter.class)) {
      bind(cls);
    }
  }

  public CCEventBus createBus() {
    Injector injector = Guice.createInjector(this);
    ServerEventBus<CCEventBus> bus = new ServerEventBus<CCEventBus>(CCEventBus.class, injector);
    return bus.getEventBus();
  }
}
