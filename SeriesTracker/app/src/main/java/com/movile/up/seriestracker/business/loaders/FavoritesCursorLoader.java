package com.movile.up.seriestracker.business.loaders;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.CursorLoader;

import com.movile.up.seriestracker.database_dbflow.FavoriteDAO;

/**
 * Created by root on 27/07/15.
 */
public class FavoritesCursorLoader extends CursorLoader {

    public FavoritesCursorLoader(Context context) {
        super(context);
    }

    @Override
    public Cursor loadInBackground() {

        return FavoriteDAO.getAllFavorites();
    }


}