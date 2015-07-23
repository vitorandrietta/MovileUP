package com.movile.up.seriestracker.interfaces.callback.restClient;

import android.content.Context;

import com.movile.up.seriestracker.interfaces.callback.presenter.ShowPresenter;
import com.movile.up.seriestracker.interfaces.callback.presenter.ShowsPresenter;

/**
 * Created by root on 22/07/15.
 */
public interface ShowClient {
    public void processShow(String show,ShowPresenter presenter,Context context);
    public void processShows( ShowsPresenter presenter,Context context);

}
