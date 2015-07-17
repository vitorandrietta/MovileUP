package com.movile.up.seriestracker.business.presenters;

import com.movile.up.seriestracker.interfaces.EpisodeDetailsView;
import com.movile.up.seriestracker.model.models.Episode;

/**
 * Created by root on 16/07/15.
 */
public class EpisodeDetailsPresenter {

    private EpisodeDetailsView mView;

    public EpisodeDetailsPresenter(EpisodeDetailsView mView){
        this.mView = mView;
    }

    public void onEpisodeDetailsSuccess(Episode episode) {
        mView.displayEpisode(episode);
    }
}
