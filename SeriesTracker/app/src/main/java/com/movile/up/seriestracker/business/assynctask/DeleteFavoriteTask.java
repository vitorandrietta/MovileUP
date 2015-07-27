package com.movile.up.seriestracker.business.assynctask;

import android.os.AsyncTask;

import com.movile.up.seriestracker.database_dbflow.FavoriteDAO;


/**
 * Created by android on 7/27/15.
 */
public class DeleteFavoriteTask extends AsyncTask<String,Void,Void>  {
    @Override
    protected Void doInBackground(String... params) {
        FavoriteDAO.delete(params[0]);
        return null;
    }
}
