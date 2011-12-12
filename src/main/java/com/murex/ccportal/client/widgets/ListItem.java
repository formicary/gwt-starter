package com.murex.ccportal.client.widgets;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author hsuleiman
 *         Date: 12/12/11
 *         Time: 12:03 PM
 */
public class ListItem extends ComplexPanel implements HasText {
  public ListItem() {
    setElement(Document.get().createLIElement());
  }

  public void add(Widget w) {
    super.add(w, getElement());
  }

  public void setId(String id) {
    getElement().setId(id);
  }

  public String getText() {
    return DOM.getInnerText(getElement());
  }

  public void setText(String text) {
    DOM.setInnerText(getElement(), (text == null) ? "" : text);
  }}
