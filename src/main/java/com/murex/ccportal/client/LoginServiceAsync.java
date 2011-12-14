package com.murex.ccportal.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author hsuleiman
 *         Date: 12/14/11
 *         Time: 10:51 AM
 */
public interface LoginServiceAsync {
  void login(String user, String password, AsyncCallback<Void> async);

  void logout(AsyncCallback<Void> async);
}
