package com.movile.up.seriestracker.business.presenters;

import android.content.Context;

import com.movile.up.seriestracker.business.restclients.ShowFragmentRestClient;
import com.movile.up.seriestracker.business.restclients.ShowRestClient;
import com.movile.up.seriestracker.interfaces.callback.presenter.ShowFragmentPresenter;
import com.movile.up.seriestracker.interfaces.view.ShowDetailsView;
import com.movile.up.seriestracker.interfaces.view.ShowFragmentDetailsView;
import com.movile.up.seriestracker.model.models.Show;

/**
 * Created by root on 27/07/15.
 */
public class ShowFragmentDetailsPresenter implements ShowFragmentPresenter {
   private Context context;
   private ShowFragmentDetailsView view;

    public ShowFragmentDetailsPresenter(Context context, ShowFragmentDetailsView view){
        this.context = context;
        this.view = view;
    }

    @Override
    public void processShow(String show) {
        ShowFragmentRestClient.processShow(show, this, this.context);
    }

    @Override
    public void onShowCallback(Show show) {
        this.view.displayShow(show);
    }
}
