package com.movile.up.seriestracker.business.assynctask;

import android.os.AsyncTask;

import com.movile.up.seriestracker.database_dbflow.FavoriteDAO;
import com.movile.up.seriestracker.database_dbflow.FavoriteEntity;


/**
 * Created by android on 7/27/15.
 */
public class InsertFavoriteTask extends AsyncTask<FavoriteEntity, Void, Void> {


    @Override
    protected Void doInBackground(FavoriteEntity... favoriteEntities) {
        FavoriteDAO.insert(favoriteEntities[0]);
        return null;
    }
}
