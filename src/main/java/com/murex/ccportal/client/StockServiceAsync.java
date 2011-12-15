package com.murex.ccportal.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.List;

public interface StockServiceAsync {
  void getStocks(AsyncCallback<List<Stock>> async);
}
