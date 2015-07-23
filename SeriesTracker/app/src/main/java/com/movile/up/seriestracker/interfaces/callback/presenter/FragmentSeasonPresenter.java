package com.movile.up.seriestracker.interfaces.callback.presenter;

import com.movile.up.seriestracker.model.models.Season;

import java.util.List;

/**
 * Created by android on 7/22/15.
 */
public interface FragmentSeasonPresenter {
    public void onSeasonFragmentCallback(List<Season> seasons);
    public void processSeasons(String show);
}
