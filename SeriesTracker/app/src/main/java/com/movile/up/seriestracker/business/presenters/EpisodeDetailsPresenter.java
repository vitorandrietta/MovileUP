package com.movile.up.seriestracker.business.presenters;

import android.content.Context;

import com.movile.up.seriestracker.business.restclients.EpisodeRestClient;
import com.movile.up.seriestracker.interfaces.callback.EpisodeClient;
import com.movile.up.seriestracker.interfaces.callback.EpisodePresenter;
import com.movile.up.seriestracker.interfaces.view.EpisodeDetailsView;
import com.movile.up.seriestracker.model.models.Episode;

/**
 * Created by root on 16/07/15.
 */
public class EpisodeDetailsPresenter implements EpisodePresenter {

    private EpisodeDetailsView mView;
    private Context context;
    private static final String TAG = EpisodePresenter.class.getSimpleName();
    private EpisodeRestClient client;

    public EpisodeDetailsPresenter(EpisodeDetailsView mView,Context context){
        this.mView = mView;
        this.context = context;
        this.client = new EpisodeRestClient();
    }

    public void presentEpisode(String show, Long season, Long episode) {
        client.processEpisode(show,season,episode,this,this.context);
    }

    @Override
    public void onEpisodeCallback(Episode episode) {
        this.mView.displayEpisode(episode);
    }
}
