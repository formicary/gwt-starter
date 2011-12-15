package com.murex.ccportal.client.presenters;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Singleton;
import com.murex.ccportal.client.CCEventBus;
import com.murex.ccportal.client.LoginService;
import com.murex.ccportal.client.LoginServiceAsync;
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

  private LoginServiceAsync service = LoginService.App.getInstance();

  private boolean loggedIn;

  public void onStart() {
    eventBus.changeNav(view);
    view.showLoggedOutNav();
    goToHome();
  }

  public void login(String user, String password) {
    //TODO some check against a backend service to see if we can login, if not then dont fire event
    service.login(user, password, new AsyncCallback<Void>() {
      @Override
      public void onFailure(Throwable caught) {
        view.showloginError(caught.getMessage());
      }

      @Override
      public void onSuccess(Void result) {
        loggedIn = true;
        eventBus.login();
        view.showLoggedInNav();
      }
    });
  }

  public void logout() {
    service.logout(new AsyncCallback<Void>() {
      @Override
      public void onFailure(Throwable caught) {
        //no point doing anything
      }

      @Override
      public void onSuccess(Void result) {
        loggedIn = false;
        view.showLoggedOutNav();
        eventBus.logout();
      }
    });
  }

  public void goToStocks() {
    eventBus.stocks();
  }

  public void goToHome() {
    eventBus.home();
  }

  public void goToContact() {
    eventBus.contacts();
  }

  public boolean isLoggedIn() {
    return loggedIn;
  }
}
