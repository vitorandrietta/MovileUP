package com.movile.up.seriestracker.business.assynctask;

import android.os.AsyncTask;

import com.movile.up.seriestracker.database_dbflow.FavoriteDAO;
import com.movile.up.seriestracker.database_dbflow.FavoriteEntity;
import com.movile.up.seriestracker.interfaces.view.ShowDetailsView;

/**
 * Created by root on 27/07/15.
 */
public class FindFavoriteAssyncTask extends AsyncTask<FavoriteEntity,Void,FavoriteEntity> {

    private  ShowDetailsView showDetailsView;

    public FindFavoriteAssyncTask(ShowDetailsView showDetailsView){
        this.showDetailsView =showDetailsView;
    }

    @Override
    protected FavoriteEntity doInBackground(FavoriteEntity... favoriteEntities) {
        return FavoriteDAO.getFavorite(favoriteEntities[0]);
    }

    @Override
    protected void onPostExecute(FavoriteEntity favoriteEntity) {
        super.onPostExecute(favoriteEntity);
        this.showDetailsView.loadButtonFirstState(favoriteEntity);
    }
}
