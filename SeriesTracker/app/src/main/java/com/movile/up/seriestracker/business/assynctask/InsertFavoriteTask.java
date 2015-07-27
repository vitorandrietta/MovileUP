package com.movile.up.seriestracker.business.assynctask;

import android.os.AsyncTask;

import com.movile.up.seriestracker.database_dbflow.FavoriteDAO;


/**
 * Created by android on 7/27/15.
 */
public class InsertFavoriteTask extends AsyncTask<String, Void, Void> {
    @Override
    protected Void doInBackground(String... params) {
        FavoriteDAO.insert(params[0]);
        return null;
    }

}
