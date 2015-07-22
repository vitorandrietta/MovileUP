package com.movile.up.seriestracker.business.presenters;

import android.content.Context;

import com.movile.up.seriestracker.business.restclients.SeasonFragmentRestClient;
import com.movile.up.seriestracker.interfaces.callback.restClient.FragmentSeasonPresenter;
import com.movile.up.seriestracker.interfaces.view.ShowSeasonsView;
import com.movile.up.seriestracker.model.models.Season;

import java.util.List;

/**
 * Created by android on 7/22/15.
 */
public class SeasonsFragmentPresenter implements FragmentSeasonPresenter {

    private SeasonFragmentRestClient client;
    private ShowSeasonsView seasonsView;
    private Context context;

    public SeasonsFragmentPresenter(ShowSeasonsView seasonsView,Context context) {
        client = new SeasonFragmentRestClient();
        this.seasonsView = seasonsView;
        this.context = context;

    }


    @Override
    public void onSeasonFragmentCallback(List<Season> seasons) {
        this.seasonsView.displaySeasons(seasons);
    }

    @Override
    public void processSeasons(String show) {
        this.client.processSeasons(show,this,context);
    }
}
