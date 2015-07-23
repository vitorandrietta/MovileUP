package com.movile.up.seriestracker.interfaces.callback.restClient;

import android.content.Context;

import com.movile.up.seriestracker.interfaces.callback.presenter.EpisodePresenter;

/**
 * Created by root on 17/07/15.
 */
public interface EpisodeClient {
    public void processEpisode(String show,long season,long episode,EpisodePresenter presenter,Context context);
}
