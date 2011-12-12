package com.murex.ccportal.client.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

import javax.inject.Singleton;

/**
 * @author hsuleiman
 *         Date: 12/12/11
 *         Time: 9:28 AM
 */
@Singleton
public class HomeView extends Composite {
  interface HomeViewUiBuilder extends UiBinder<HTMLPanel, HomeView> {
  }

  private static HomeViewUiBuilder binder = GWT.create(HomeViewUiBuilder.class);

  public HomeView() {
    initWidget(binder.createAndBindUi(this));
  }
}
