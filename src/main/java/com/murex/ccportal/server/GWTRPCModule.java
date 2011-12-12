package com.murex.ccportal.server;

import com.google.inject.servlet.ServletModule;

/**
 * @author hsuleiman
 *         Date: 12/9/11
 *         Time: 9:31 AM
 */
public class GWTRPCModule extends ServletModule {
  @Override
  protected void configureServlets() {
    serve("/CCPortal/GWT.rpc").with(GuiceRemoteServiceServlet.class);
  }
}
