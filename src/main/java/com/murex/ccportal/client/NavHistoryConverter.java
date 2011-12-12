package com.murex.ccportal.client;

import com.mvp4g.client.annotation.History;
import com.mvp4g.client.history.HistoryConverter;

/**
 * @author hsuleiman
 *         Date: 12/12/11
 *         Time: 3:53 PM
 */
@History(type = History.HistoryConverterType.SIMPLE)
public class NavHistoryConverter implements HistoryConverter<CCEventBus> {

  @Override
  public void convertFromToken(String historyName, String param, CCEventBus eventBus) {
    Views v = Views.valueOf(historyName);
    switch(v) {
      case stocks:
        eventBus.stocks();
        break;
      case home:
        eventBus.home();
        break;
    }
  }

  public String convertToToken(String event) {
    return "";
  }

  @Override
  public boolean isCrawlable() {
    return false;
  }
}
