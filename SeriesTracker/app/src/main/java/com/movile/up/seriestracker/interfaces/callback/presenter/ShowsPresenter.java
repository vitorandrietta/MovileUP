package com.movile.up.seriestracker.interfaces.callback.presenter;

import com.movile.up.seriestracker.interfaces.view.ShowsDetailsView;
import com.movile.up.seriestracker.model.models.Show;

import java.util.List;

/**
 * Created by android on 7/23/15.
 */
public interface ShowsPresenter {

    public void onShowsCallback(List<Show> shows);
    public void processShows();

}
