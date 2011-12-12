package com.murex.ccportal.client.presenters;

import com.google.gwt.user.client.ui.IsWidget;
import com.murex.ccportal.client.CCEventBus;
import com.murex.ccportal.client.views.MainView;
import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;

import javax.inject.Singleton;

/**
 * @author hsuleiman
 *         Date: 12/9/11
 *         Time: 2:36 PM
 */
@Presenter(view = MainView.class)
@Singleton
public class MainViewPresenter extends BasePresenter<MainView, CCEventBus>{
  public void onChangeBody(IsWidget widget) {
    view.setBody(widget);
  }

  public void onChangeNav(IsWidget widget) {
    view.setNav(widget);
  }
}
