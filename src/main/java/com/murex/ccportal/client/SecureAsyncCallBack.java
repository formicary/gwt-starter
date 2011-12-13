package com.murex.ccportal.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.murex.ccportal.client.exceptions.AuthenticationException;
import com.murex.ccportal.client.exceptions.AuthorizationException;

/**
 * @author hsuleiman
 *         Date: 12/13/11
 *         Time: 4:18 PM
 */
public abstract class SecureAsyncCallBack<T> implements AsyncCallback<T> {
  public void onFailure(Throwable caught) {
    if(caught instanceof AuthenticationException) {
      //redirect to login page
    }
    else if(caught instanceof AuthorizationException) {
      //tell the user he doesn't have rights to perform this operation
    }
    try {
      //allow your application to handle the exception
      handleFailure(caught);
    }
    catch(Throwable e) {
      //default application-wide error handling
    }
  }

  public void handleFailure(Throwable t) throws Throwable {
    throw t;
  }
}
