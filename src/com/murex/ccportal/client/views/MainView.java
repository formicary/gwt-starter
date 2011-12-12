package com.murex.ccportal.client.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;

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

  @UiField SimplePanel body;
  @UiField SimplePanel nav;

  @Inject
  public MainView() {
    initWidget(binder.createAndBindUi(this));
  }

  public void setBody(IsWidget widget) {
    body.setWidget(widget);
  }

  public void setNav(IsWidget widget) {
    nav.setWidget(widget);
  }
}
