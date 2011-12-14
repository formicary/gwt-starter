package com.murex.ccportal.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.murex.ccportal.client.exceptions.ValidationException;

/**
 * @author hsuleiman
 *         Date: 12/14/11
 *         Time: 10:51 AM
 */
@RemoteServiceRelativePath("GWT.rpc")
public interface LoginService extends RemoteService {
  /**
   * Utility/Convenience class.
   * Use LoginService.App.getInstance() to access static instance of LoginServiceAsync
   */
  public static class App {
    private static final LoginServiceAsync ourInstance = (LoginServiceAsync) GWT.create(LoginService.class);

    public static LoginServiceAsync getInstance() {
      return ourInstance;
    }
  }

  public void login(String user, String password) throws ValidationException;
  public void logout();
}
