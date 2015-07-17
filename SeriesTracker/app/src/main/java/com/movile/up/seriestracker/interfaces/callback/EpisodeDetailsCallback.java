package com.movile.up.seriestracker.interfaces.callback;

import com.movile.up.seriestracker.model.models.Episode;

/**
 * Created by android on 7/17/15.
 */
public interface EpisodeDetailsCallback {
 public void presentEpisode(String show, Long season, Long episode);
}
