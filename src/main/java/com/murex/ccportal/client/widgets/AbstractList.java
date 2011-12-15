package com.murex.ccportal.client.widgets;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author hsuleiman
 *         Date: 12/15/11
 *         Time: 5:39 PM
 */
public class AbstractList extends ComplexPanel {
  public void add(Widget w) {
    add(w, getElement());
  }

  public void setId(String id) {
    getElement().setId(id);
  }
}
