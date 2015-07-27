package com.movile.up.seriestracker.business.presenters;

import android.content.Context;

import com.movile.up.seriestracker.business.assynctask.FindFavoriteAssyncTask;
import com.movile.up.seriestracker.business.restclients.ShowRestClient;
import com.movile.up.seriestracker.database_dbflow.FavoriteEntity;
import com.movile.up.seriestracker.interfaces.callback.presenter.ShowPresenter;
import com.movile.up.seriestracker.interfaces.view.ShowDetailsView;
import com.movile.up.seriestracker.model.models.Season;
import com.movile.up.seriestracker.model.models.Show;

/**
 * Created by root on 22/07/15.
 */
public class ShowDetailsPresenter implements ShowPresenter {
    private Context context;
    private ShowDetailsView view;


    public ShowDetailsPresenter(Context context, ShowDetailsView view){
        this.context = context;
        this.view = view;
    }

    @Override
    public void processShow(String show) {
        ShowRestClient.processShow(show, this, this.context);
    }

    @Override
    public void onShowCallback(Show show) {
        this.view.displayShow(show);
    }

    @Override
    public void loadFavoriteButton(FavoriteEntity favoriteEntity, ShowDetailsView showDetailsView) {
        new FindFavoriteAssyncTask(showDetailsView).execute(favoriteEntity);
    }


}
