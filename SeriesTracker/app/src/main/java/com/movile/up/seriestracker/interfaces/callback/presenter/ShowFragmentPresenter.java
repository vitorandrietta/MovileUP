package com.movile.up.seriestracker.interfaces.callback.presenter;

import com.movile.up.seriestracker.model.models.Show;

/**
 * Created by root on 27/07/15.
 */
public interface ShowFragmentPresenter {
    public void processShow(String show);
    public void onShowCallback (Show show);
}
