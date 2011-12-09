package com.murex.ccportal.client.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

import javax.inject.Inject;

/**
 * @author hsuleiman
 *         Date: 12/9/11
 *         Time: 2:29 PM
 */
public class MainView extends Composite {

  interface MainViewUiBuilder extends UiBinder<HTMLPanel, MainView> {
  }

  private static MainViewUiBuilder binder = GWT.create(MainViewUiBuilder.class);

  @Inject
  public MainView() {
    initWidget(binder.createAndBindUi(this));
  }
}
