package com.murex.ccportal.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.List;

/**
 * @author hsuleiman
 *         Date: 12/9/11
 *         Time: 9:35 AM
 */
@RemoteServiceRelativePath("GWT.rpc")
public interface StockService extends RemoteService {
  public List<String> getStocks();
}
