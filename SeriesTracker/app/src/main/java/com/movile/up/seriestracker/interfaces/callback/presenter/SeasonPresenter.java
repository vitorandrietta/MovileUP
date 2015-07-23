package com.movile.up.seriestracker.interfaces.callback.presenter;

import com.movile.up.seriestracker.model.models.Episode;
import com.movile.up.seriestracker.model.models.Season;

import java.util.List;

/**
 * Created by root on 20/07/15.
 */
public interface SeasonPresenter {
    public void onSeasonCallback(Season season);
    public void presentSeason(String show, Long season);
    public void onSeasonEpisodesCallback(List<Episode> episodes);
}
