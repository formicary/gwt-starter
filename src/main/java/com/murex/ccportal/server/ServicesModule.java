package com.murex.ccportal.server;

import com.google.inject.AbstractModule;
import com.murex.ccportal.client.StockService;

/**
 * @author hsuleiman
 *         Date: 12/9/11
 *         Time: 10:48 AM
 */
public class ServicesModule extends AbstractModule {
  @Override
  public void configure() {
    bind(StockService.class).to(StockServiceImpl.class);
  }
}
