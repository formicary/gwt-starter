package com.murex.ccportal.client;

import java.io.Serializable;
import java.util.Date;

/**
 * @author hsuleiman
 *         Date: 12/15/11
 *         Time: 5:44 PM
 */
public class Stock implements Serializable {
  private String symbol;
  private double price;
  private Date lastUpdated;

  public Stock() {
  }

  public Stock(String symbol) {
    this.symbol = symbol;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public Date getLastUpdated() {
    return lastUpdated;
  }

  public void setLastUpdated(Date lastUpdated) {
    this.lastUpdated = lastUpdated;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Stock)) return false;

    Stock stock = (Stock) o;

    if (!symbol.equals(stock.symbol)) return false;

    return true;
  }

  @Override
  public int hashCode() {
    return symbol.hashCode();
  }
}
