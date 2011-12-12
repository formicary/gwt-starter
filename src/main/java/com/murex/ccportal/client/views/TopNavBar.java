package com.murex.ccportal.client.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;
import com.google.inject.Inject;
import com.murex.ccportal.client.presenters.TopNavBarPresenter;
import com.murex.ccportal.client.widgets.ListItem;
import com.murex.ccportal.client.widgets.UnorderedList;
import com.mvp4g.client.view.ReverseViewInterface;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hsuleiman
 *         Date: 12/9/11
 *         Time: 10:15 AM
 */
@Singleton
public class TopNavBar extends Composite implements ReverseViewInterface<TopNavBarPresenter> {

  @Inject
  private TopNavBarPresenter presenter;

  interface TopNavBarUiBinder extends UiBinder<HTMLPanel, TopNavBar> {
  }
  private static TopNavBarUiBinder binder = GWT.create(TopNavBarUiBinder.class);

  @UiField Button loginButton;
  @UiField TextBox user;
  @UiField PasswordTextBox password;
  @UiField UnorderedList nav;
  private List<ListItem> items = new ArrayList<ListItem>();
  private NavClickHandler navClickHandler = new NavClickHandler();

  public TopNavBar() {
    initWidget(binder.createAndBindUi(this));
    //stupid gwt doesn't support placeholder attribute
    user.getElement().setAttribute("placeholder", "Username");
    password.getElement().setAttribute("placeholder", "Password");
  }

  @Override
  public void setPresenter(TopNavBarPresenter presenter) {
    this.presenter = presenter;
  }

  @Override
  public TopNavBarPresenter getPresenter() {
    return presenter;
  }

  @UiHandler("loginButton")
  public void handleClick(ClickEvent event) {
    presenter.login(user.getValue(), password.getValue());
  }

  private void resetNavSelection() {
    for (ListItem item : items) {
      item.removeStyleName("active");
    }
  }

  enum Views {
    stocks,
    home,
    contact
  }

  @UiHandler({"password", "user"})
  public void handleKeyPress(KeyPressEvent event) {
    if(event.getCharCode() == KeyCodes.KEY_ENTER) {
      presenter.login(user.getValue(), password.getValue());
    }
  }

  private ListItem createNavItem(Views link, String name, boolean active) {
    ListItem li = new ListItem();
    Anchor a = new Anchor(name, '#'+ link.name());
    a.setName("nav-" + link.name());
    a.addClickHandler(navClickHandler);
    if(active) {
      li.addStyleName("active");
    }
    li.add(a);
    return li;
  }

  private class NavClickHandler implements ClickHandler {
    @Override
    public void onClick(ClickEvent event) {
      resetNavSelection();
      Anchor anchor = (Anchor) event.getSource();
      anchor.getParent().addStyleName("active");
      //strip out 'nav-' prefix
      Views view = Views.valueOf(anchor.getName().substring(4));
      switch(view) {
        case stocks:
          presenter.goToStocks();
          return;
        case home:
          presenter.goToHome();
          return;
        case contact:
          presenter.goToContact();
      }
    }
  }
  private void syncItems() {
    nav.clear();
    for (ListItem item : items) {
      nav.add(item);
    }
  }

  public void showLoggedOutNav() {
    items.clear();
    items.add(createNavItem(Views.home, "Home", true));
    items.add(createNavItem(Views.stocks, "About", false));
    items.add(createNavItem(Views.contact, "Contact", false));
    syncItems();
  }

  public void showLoggedInNav() {
    items.clear();
    items.add(createNavItem(Views.home, "Home", true));
    items.add(createNavItem(Views.home, "LoggedInStuff", false));
    items.add(createNavItem(Views.home, "Reports", false));
    items.add(createNavItem(Views.home, "Contact", false));
    syncItems();
    loginButton.setText("Sign out");
  }
}