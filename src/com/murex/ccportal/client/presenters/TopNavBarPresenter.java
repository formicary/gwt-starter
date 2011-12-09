package com.murex.ccportal.client.presenters;

import com.google.inject.Singleton;
import com.murex.ccportal.client.CCEventBus;
import com.murex.ccportal.client.views.TopNavBar;
import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;

/**
 * @author hsuleiman
 *         Date: 12/9/11
 *         Time: 2:04 PM
 */
@Presenter(view = TopNavBar.class)
@Singleton
public class TopNavBarPresenter extends BasePresenter<TopNavBar, CCEventBus> {

  public TopNavBarPresenter() {
    System.out.println("Creating topnavbar presenter");
  }

  public void onStart() {
    view.showLoggedOutNav();
  }

  public void login(String user, String password) {
    eventBus.login();
  }

  public void onLogin() {
    view.showLoggedInNav();
  }
}
