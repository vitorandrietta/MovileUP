package com.movile.up.seriestracker.business.presenters;

import android.content.Context;

import com.movile.up.seriestracker.business.restclients.ShowRestClient;
import com.movile.up.seriestracker.interfaces.callback.presenter.ShowPresenter;
import com.movile.up.seriestracker.interfaces.view.ShowDetailsView;
import com.movile.up.seriestracker.model.models.Season;
import com.movile.up.seriestracker.model.models.Show;

/**
 * Created by root on 22/07/15.
 */
public class ShowDetailsPresenter implements ShowPresenter {
        private Context context;
    private ShowDetailsView view;
    private ShowRestClient client;

    public ShowDetailsPresenter(Context context, ShowDetailsView view){
        this.context = context;
        this.view = view;
        client = new ShowRestClient();

    }

    @Override
    public void processShow(String show) {
        client.processShow(show,this,this.context);
    }

    @Override
    public void onShowCallback(Show show) {
        this.view.displayShow(show);
    }
}
