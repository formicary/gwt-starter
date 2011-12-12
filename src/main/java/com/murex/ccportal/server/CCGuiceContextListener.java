package com.murex.ccportal.server;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

/**
 * @author hsuleiman
 *         Date: 12/9/11
 *         Time: 9:30 AM
 */
public class CCGuiceContextListener extends GuiceServletContextListener {
  @Override
  protected Injector getInjector() {
    return Guice.createInjector(new GWTRPCModule(), new ServicesModule());
  }
}
