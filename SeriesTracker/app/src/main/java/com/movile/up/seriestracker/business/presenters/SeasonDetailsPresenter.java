package com.movile.up.seriestracker.business.presenters;

import android.content.Context;

import com.movile.up.seriestracker.business.restclients.SeasonRestClient;
import com.movile.up.seriestracker.interfaces.callback.presenter.SeasonPresenter;
import com.movile.up.seriestracker.interfaces.view.SeasonDetailsView;
import com.movile.up.seriestracker.model.models.Episode;
import com.movile.up.seriestracker.model.models.Season;

import java.util.List;

/**
 * Created by root on 19/07/15.
 */
public class SeasonDetailsPresenter implements SeasonPresenter {

    private SeasonDetailsView mView;
    private Context context;
    private static final String TAG = SeasonPresenter.class.getSimpleName();

    public SeasonDetailsPresenter(SeasonDetailsView mView,Context context){
        this.mView = mView;
        this.context = context;

    }


    @Override
    public void onSeasonCallback(Season season) {
        this.mView.displaySeasonDetails(season);
     }



    @Override
    public void presentSeason(String show, Long season) {
        SeasonRestClient.processSeasonEpisodes(show, season, this, context);
        SeasonRestClient.processSeason(show, season, this, this.context);
    }

    @Override
    public void onSeasonEpisodesCallback(List<Episode> episodes) {
        this.mView.displaySeasonEpisodes(episodes);
    }
}
