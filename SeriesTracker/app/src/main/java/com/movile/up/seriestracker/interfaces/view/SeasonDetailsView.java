package com.movile.up.seriestracker.interfaces.view;

import com.movile.up.seriestracker.business.holders.EpisodeListItemHolder;
import com.movile.up.seriestracker.model.models.Season;

import java.util.ArrayList;

/**
 * Created by root on 19/07/15.
 */
public interface SeasonDetailsView {
    public void displaySeasonDetails(Season season);
    public void displaySeasonEpisodes(EpisodeListItemHolder holder,ArrayList<String> episodeDetails, int pos);
}
