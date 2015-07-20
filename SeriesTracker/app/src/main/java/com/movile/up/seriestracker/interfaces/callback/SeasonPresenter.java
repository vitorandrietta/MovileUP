package com.movile.up.seriestracker.interfaces.callback;

import com.movile.up.seriestracker.model.models.Season;

/**
 * Created by root on 20/07/15.
 */
public interface SeasonPresenter {
    public void onSeasonCallback(Season season);
    public void presentSeason(String show, Long season);
}
