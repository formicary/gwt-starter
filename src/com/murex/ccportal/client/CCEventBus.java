package com.murex.ccportal.client;

import com.murex.ccportal.client.presenters.TopNavBarPresenter;
import com.murex.ccportal.client.presenters.StockPresenter;
import com.murex.ccportal.client.views.MainView;
import com.mvp4g.client.annotation.Debug;
import com.mvp4g.client.annotation.Event;
import com.mvp4g.client.annotation.Events;
import com.mvp4g.client.annotation.Start;
import com.mvp4g.client.event.EventBus;

/**
 * @author hsuleiman
 *         Date: 12/9/11
 *         Time: 11:57 AM
 */
@Events(startView = MainView.class)
@Debug
public interface CCEventBus extends EventBus {
  @Start
  @Event(handlers = {TopNavBarPresenter.class})
  public void start();

  @Event(handlers = {StockPresenter.class, TopNavBarPresenter.class})
  public void login();
}
