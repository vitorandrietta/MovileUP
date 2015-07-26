package com.movile.up.seriestracker.business.presenters;

import android.content.Context;

import com.movile.up.seriestracker.business.restclients.ShowRestClient;
import com.movile.up.seriestracker.interfaces.callback.presenter.ShowsPresenter;
import com.movile.up.seriestracker.interfaces.view.ShowsDetailsView;
import com.movile.up.seriestracker.model.models.Show;

import java.util.List;

/**
 * Created by android on 7/23/15.
 */
public class ShowsDetailsPresenter implements ShowsPresenter{

    private Context context;
    private ShowsDetailsView mView;


    public ShowsDetailsPresenter(Context context,ShowsDetailsView showsView){
        this.context = context;
        this.mView = showsView;

    }

    @Override
    public void onShowsCallback(List<Show> shows) {
        mView.displayShowsDetails(shows);

    }

    @Override
    public void processShows() {
        ShowRestClient.processShows(this, this.context);
    }
}
