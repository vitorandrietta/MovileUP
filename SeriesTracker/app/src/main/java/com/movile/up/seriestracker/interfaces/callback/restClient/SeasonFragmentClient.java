package com.movile.up.seriestracker.interfaces.callback.restClient;

import android.content.Context;

import com.movile.up.seriestracker.interfaces.callback.presenter.FragmentSeasonPresenter;

/**
 * Created by android on 7/22/15.
 */
public interface SeasonFragmentClient {
    public void processSeasons(String show,FragmentSeasonPresenter presenter,Context context);
}
