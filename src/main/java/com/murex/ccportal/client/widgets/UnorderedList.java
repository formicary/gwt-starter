package com.murex.ccportal.client.widgets;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author hsuleiman
 *         Date: 12/12/11
 *         Time: 12:01 PM
 */
public class UnorderedList extends ComplexPanel {
  public UnorderedList() {
    setElement(Document.get().createULElement());
  }

  public void add(Widget w) {
    super.add(w, getElement());
  }

  public void setId(String id) {
    getElement().setId(id);
  }
}
