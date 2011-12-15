package com.murex.ccportal.server;

import com.murex.ccportal.client.Stock;
import com.murex.ccportal.client.StockService;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hsuleiman
 *         Date: 12/9/11
 *         Time: 9:37 AM
 */
@Singleton
public class StockServiceImpl implements StockService{
  @Override
  public List<Stock> getStocks() {
    String[] names = new String[]{"GOOG", "ORCL", "GM", "MSFT"};
    List<Stock> stocks = new ArrayList<Stock>();
    for (String name : names) {
      Stock s = new Stock(name);
      s.setPrice(10);
      stocks.add(s);
    }
    return stocks;
  }
}
