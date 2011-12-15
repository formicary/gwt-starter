package com.murex.ccportal.client.widgets;

import com.google.gwt.dom.client.Document;

/**
 * @author hsuleiman
 *         Date: 12/12/11
 *         Time: 12:01 PM
 */
public class OrderedList extends AbstractList {
  public OrderedList() {
    setElement(Document.get().createOLElement());
  }
}
