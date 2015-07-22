package com.movile.up.seriestracker.interfaces.callback.restClient;

import android.content.Context;

/**
 * Created by root on 20/07/15.
 */
public interface SeasonClient {
    public void processSeason(String show,long season,SeasonPresenter presenter,Context context);
    public void processSeasonEpisodes(String show,long season,SeasonPresenter presenter,Context context);
}
