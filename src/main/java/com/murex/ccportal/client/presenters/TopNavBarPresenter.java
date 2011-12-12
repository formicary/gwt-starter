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

  private boolean loggedIn;

  public void onStart() {
    eventBus.changeNav(view);
    view.showLoggedOutNav();
    goToHome();
  }

  public void login(String user, String password) {
    //TODO some check against a backend service to see if we can login, if not then dont fire event
    loggedIn = true;
    eventBus.login();
    view.showLoggedInNav();
  }

  public void logout() {
    loggedIn = false;
    view.showLoggedOutNav();
    eventBus.logout();
  }

  public void goToStocks() {
    eventBus.goToStocks();
  }

  public void goToHome() {
    eventBus.goToHome();
  }

  public void goToContact() {
    eventBus.goToContact();
  }

  public boolean isLoggedIn() {
    return loggedIn;
  }
}
