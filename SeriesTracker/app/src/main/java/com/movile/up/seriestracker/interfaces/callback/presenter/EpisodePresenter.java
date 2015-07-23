package com.movile.up.seriestracker.interfaces.callback.presenter;

import com.movile.up.seriestracker.model.models.Episode;

/**
 * Created by android on 7/17/15.
 */
public interface EpisodePresenter {
  public void onEpisodeCallback(Episode episode);
  public void presentEpisode(String show, Long season, Long episode);
}
