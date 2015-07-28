package com.movile.up.seriestracker.business.loadercallback;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.movile.up.seriestracker.business.loaders.FavoritesCursorLoader;
import com.movile.up.seriestracker.interfaces.view.FavoritesFragmentDetailsView;
import com.movile.up.seriestracker.model.models.Favorite;

/**
 * Created by root on 27/07/15.
 */
public class FavoritesLoaderCallback implements LoaderManager.LoaderCallbacks<Cursor> {

    private FavoritesFragmentDetailsView favoritesFragmentDetailsView;
    private Context context;

    public FavoritesLoaderCallback(FavoritesFragmentDetailsView fragmentDetailsView, Context context){
        this.favoritesFragmentDetailsView = fragmentDetailsView;
        this.context = context;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new FavoritesCursorLoader(this.context);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        this.favoritesFragmentDetailsView.displayFavorites(data);
     }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
