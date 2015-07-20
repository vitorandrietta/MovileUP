package com.movile.up.seriestracker.interfaces.view;

import com.movile.up.seriestracker.model.models.Episode;
import com.movile.up.seriestracker.model.models.Season;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 19/07/15.
 */
public interface SeasonDetailsView {
    public void displaySeasonDetails(Season season);
    public void displaySeasonEpisodes(List<Episode> episodes);
}
