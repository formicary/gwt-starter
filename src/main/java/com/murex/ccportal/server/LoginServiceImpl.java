package com.murex.ccportal.server;

import com.murex.ccportal.client.LoginService;
import com.murex.ccportal.client.exceptions.ValidationException;

import javax.inject.Singleton;

/**
 * @author hsuleiman
 *         Date: 12/14/11
 *         Time: 10:53 AM
 */
@Singleton
public class LoginServiceImpl implements LoginService {
  @Override
  public void login(String user, String password) {
    if(password.length() == 0) {
      throw new ValidationException("Invalid password");
    }
  }

  @Override
  public void logout() {
  }
}
