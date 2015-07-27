package com.movile.up.seriestracker.business.assynctask;

import android.os.AsyncTask;

import com.movile.up.seriestracker.database_dbflow.FavoriteDAO;
import com.movile.up.seriestracker.interfaces.view.ShowDetailsView;


/**
 * Created by android on 7/27/15.
 */
public class IsFavoriteTask extends AsyncTask<String,Void,Boolean>{

    public ShowDetailsView showDetailsView;

    public IsFavoriteTask(ShowDetailsView showsDetailsView){
        this.showDetailsView = showsDetailsView;

    }

    @Override
    protected Boolean doInBackground(String... params) {
        return FavoriteDAO.isFavorite(params[0]);
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        this.showDetailsView.changeButtonVisualState(aBoolean);
        this.showDetailsView.setButtonState(aBoolean);
    }
}
