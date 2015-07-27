package com.movile.up.seriestracker.interfaces.view;

import com.movile.up.seriestracker.database_dbflow.FavoriteEntity;
import com.movile.up.seriestracker.model.models.Show;

/**
 * Created by root on 22/07/15.
 */
public interface ShowDetailsView {
    public void displayShow(Show show);
    public void changeButtonVisualState(boolean state);
    public void loadButtonFirstState(FavoriteEntity entity);
}
