package com.murex.ccportal.client.presenters;

import com.murex.ccportal.client.CCEventBus;
import com.murex.ccportal.client.views.MainView;
import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;

/**
 * @author hsuleiman
 *         Date: 12/9/11
 *         Time: 2:36 PM
 */
@Presenter(view = MainView.class)
public class MainViewPresenter extends BasePresenter<MainView, CCEventBus>{
}
