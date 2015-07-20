package com.movile.up.seriestracker.interfaces.callback;

import android.content.Context;

/**
 * Created by root on 17/07/15.
 */
public interface EpisodeClient {
    public void processEpisode(String show,long season,long episode,EpisodePresenter presenter,Context context);
}
