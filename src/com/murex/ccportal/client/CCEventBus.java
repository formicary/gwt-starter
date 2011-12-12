package com.murex.ccportal.client;

import com.google.gwt.user.client.ui.IsWidget;
import com.murex.ccportal.client.presenters.HomePresenter;
import com.murex.ccportal.client.presenters.MainViewPresenter;
import com.murex.ccportal.client.presenters.StockPresenter;
import com.murex.ccportal.client.presenters.TopNavBarPresenter;
import com.murex.ccportal.client.views.MainView;
import com.mvp4g.client.annotation.Debug;
import com.mvp4g.client.annotation.Event;
import com.mvp4g.client.annotation.Events;
import com.mvp4g.client.annotation.Start;
import com.mvp4g.client.event.EventBus;

import javax.inject.Singleton;

/**
 * @author hsuleiman
 *         Date: 12/9/11
 *         Time: 11:57 AM
 */
@Events(startView = MainView.class)
@Singleton
@Debug
public interface CCEventBus extends EventBus {
  @Start
  @Event(handlers = {TopNavBarPresenter.class})
  void start();

  @Event(handlers = {StockPresenter.class, TopNavBarPresenter.class})
  void login();

  @Event(handlers = HomePresenter.class)
  void goToHome();

  @Event(handlers = StockPresenter.class)
  void goToStocks();

  @Event
  void goToContact();

  @Event(handlers = MainViewPresenter.class)
  void changeBody(IsWidget view);

  @Event(handlers = MainViewPresenter.class)
  void changeNav(IsWidget view);
}
