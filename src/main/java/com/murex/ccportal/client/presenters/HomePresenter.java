package com.murex.ccportal.client.presenters;

import com.murex.ccportal.client.CCEventBus;
import com.murex.ccportal.client.views.HomeView;
import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;

import javax.inject.Singleton;

/**
 * @author hsuleiman
 *         Date: 12/12/11
 *         Time: 9:27 AM
 */
@Presenter(view = HomeView.class)
@Singleton
public class HomePresenter extends BasePresenter<HomeView, CCEventBus> {
  public void onHome() {
    eventBus.changeBody(view);
  }
}
