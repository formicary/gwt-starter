package com.murex.ccportal.client.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.LIElement;
import com.google.gwt.dom.client.UListElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;
import com.google.inject.Inject;
import com.murex.ccportal.client.presenters.TopNavBarPresenter;

/**
 * @author hsuleiman
 *         Date: 12/9/11
 *         Time: 10:15 AM
 */
public class TopNavBar extends Composite {

  @Inject
  private TopNavBarPresenter presenter;

  interface TopNavBarUiBinder extends UiBinder<HTMLPanel, TopNavBar> {
  }
  private static TopNavBarUiBinder binder = GWT.create(TopNavBarUiBinder.class);

  @UiField Button loginButton;
  @UiField TextBox user;
  @UiField PasswordTextBox password;
  @UiField UListElement nav;
  @UiField Anchor stocks;

  public TopNavBar() {
    initWidget(binder.createAndBindUi(this));
    //stupid gwt doesn't support placeholder attribute
    user.getElement().setAttribute("placeholder", "Username");
    password.getElement().setAttribute("placeholder", "Password");
  }

  @UiHandler({"stocks", "loginButton"})
  public void handleClick(ClickEvent event) {
    if(event.getSource() == loginButton)
      presenter.login(user.getValue(), password.getValue());
    else {
     // presenter.viewSelected(((Anchor)event.getSource()).getName());
    }
  }

  @UiHandler({"password", "user"})
  public void handleKeyPress(KeyPressEvent event) {
    if(event.getCharCode() == KeyCodes.KEY_ENTER) {
      presenter.login(user.getValue(), password.getValue());
    }
  }

  private LIElement createNavItem(String link, String name, boolean active) {
    LIElement li = Document.get().createLIElement();
    AnchorElement a = Document.get().createAnchorElement();
    a.setHref(link);
    a.appendChild(Document.get().createTextNode(name));
    if(active) {
      a.addClassName("active");
    }
    li.appendChild(a);
    return li;
  }

  public void showLoggedOutNav() {
    nav.setInnerHTML("");
    nav.appendChild(createNavItem("/", "Home", true));
    nav.appendChild(createNavItem("#about", "About", false));
    nav.appendChild(createNavItem("#contact", "Contact", false));
  }

  public void showLoggedInNav() {
    nav.setInnerHTML("");
    nav.appendChild(createNavItem("/", "Home", true));
    nav.appendChild(createNavItem("#stuff", "LoggedInStuff", false));
    nav.appendChild(createNavItem("#stuff2", "Reports", false));
    nav.appendChild(createNavItem("#about", "About", false));
    nav.appendChild(createNavItem("#contact", "Contact", false));
  }
}
