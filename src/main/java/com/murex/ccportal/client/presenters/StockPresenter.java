package com.murex.ccportal.client.presenters;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Timer;
import com.google.inject.Singleton;
import com.murex.ccportal.client.CCEventBus;
import com.murex.ccportal.client.SecureAsyncCallBack;
import com.murex.ccportal.client.StockService;
import com.murex.ccportal.client.StockServiceAsync;
import com.murex.ccportal.client.views.StockWatcher;
import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;

import java.util.List;

/**
 * @author hsuleiman
 *         Date: 12/9/11
 *         Time: 12:05 PM
 */
@Presenter(view = StockWatcher.class)
@Singleton
public class StockPresenter extends BasePresenter<StockWatcher, CCEventBus> {

  private StockServiceAsync service = GWT.create(StockService.class);
  private Timer refreshTimer;

  public void onLogin() {
    refreshTimer = new Timer() {
      @Override
      public void run() {
        refreshWatchList();
      }
    };
    refreshWatchList();
    refreshTimer.scheduleRepeating(1000 * 10);
  }

  public void onLogout() {
    if(refreshTimer != null)
      refreshTimer.cancel();
  }

  public void onStocks() {
    eventBus.changeBody(view);
  }

  private void refreshWatchList() {
    service.getStocks(new SecureAsyncCallBack<List<String>>() {
      @Override
      public void onFailure(Throwable caught) {
        //todo
      }

      @Override
      public void onSuccess(List<String> result) {
        for (String s : result) {
          view.addStock(s);
        }
      }
    });
  }

}
