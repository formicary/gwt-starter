package com.murex.ccportal.client.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.murex.ccportal.client.Stock;
import com.murex.ccportal.client.presenters.StockPresenter;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hsuleiman
 *         Date: 12/9/11
 *         Time: 2:33 PM
 */
public class StockWatcher extends Composite {
  private List<Stock> stocks = new ArrayList<Stock>();
  @Inject
  private StockPresenter presenter;
  @UiField TextBox newSymbolTextBox;
  @UiField Button addStockButton;
  @UiField Label lastUpdatedLabel;
  @UiField FlexTable stocksFlexTable;

  interface StockWatcherUiBinder extends UiBinder<HTMLPanel, StockWatcher> {
  }

  private static StockWatcherUiBinder binder = GWT.create(StockWatcherUiBinder.class);

  public StockWatcher() {
    initWidget(binder.createAndBindUi(this));
    stocksFlexTable.setText(0, 0, "Symbol");
    stocksFlexTable.setText(0, 1, "Price");
    stocksFlexTable.setText(0, 2, "Change");
    stocksFlexTable.setText(0, 3, "Remove");
  }

  @UiHandler("newSymbolTextBox")
  void onKeyPress(KeyPressEvent event) {
    if (event.getCharCode() == KeyCodes.KEY_ENTER) {
      String symbol = newSymbolTextBox.getText().toUpperCase().trim();
      addStock(new Stock(symbol));
    }
  }

  @UiHandler("addStockButton")
  void handleClick(ClickEvent e) {
    String symbol = newSymbolTextBox.getText().toUpperCase().trim();
    addStock(new Stock(symbol));
  }

  public void addStock(final Stock stock) {
    newSymbolTextBox.setFocus(true);
    if (!stock.getSymbol().matches("^[0-9A-Z\\.]{1,10}$")) {
      Window.alert("'" + stock.getSymbol() + "' is not a valid symbol");
      newSymbolTextBox.selectAll();
      return;
    }
    newSymbolTextBox.setText("");
    if (stocks.contains(stock)) {
      return;
    }

    int row = stocksFlexTable.getRowCount();
    stocks.add(stock);
    stocksFlexTable.setText(row, 0, stock.getSymbol());
    stocksFlexTable.setText(row, 1, Double.toString(stock.getPrice()));

    Button removeStockButton = new Button("x");
    //removeStockButton.setStylePrimaryName("btn primary");
    removeStockButton.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        int index = stocks.indexOf(stock);
        stocks.remove(index);
        stocksFlexTable.removeRow(index + 1);
      }
    });
    stocksFlexTable.setWidget(row, 3, removeStockButton);
  }

}