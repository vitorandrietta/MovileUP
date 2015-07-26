package com.movile.up.seriestracker.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by root on 26/07/15.
 */
public class SeriesTrackerDbHelper extends SQLiteOpenHelper {

    public SeriesTrackerDbHelper(Context context) {
        super(context, DatabaseConfiguration.DATABASE_NAME,
                null, DatabaseConfiguration.DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase database) {
        database.execSQL(FavoriteEntity.FavoriteEntityFields.createSql());
    }

    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {

    }
}