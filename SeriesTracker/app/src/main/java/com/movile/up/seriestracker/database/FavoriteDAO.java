package com.movile.up.seriestracker.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by root on 26/07/15.
 */
public class FavoriteDAO {

    private SeriesTrackerDbHelper dbHelper;

    public FavoriteDAO(Context context){

      dbHelper = new SeriesTrackerDbHelper(context);

    }

    public void insert(ContentValues contentValues){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.insert(FavoriteEntity.FavoriteEntityFields.TABLE_NAME,null,contentValues);
   }

   public boolean isFavorite(String show){
       SQLiteDatabase database = dbHelper.getReadableDatabase();
       String [] columns = new String[]{FavoriteEntity.FavoriteEntityFields.COLUMN_SLUG};
       String selection = FavoriteEntity.FavoriteEntityFields.COLUMN_SLUG.
               concat("=").concat("'"+show+"'");

     Cursor cursor = database.query(FavoriteEntity.FavoriteEntityFields.TABLE_NAME, columns,
             selection, null, null, null, null);

       return  cursor.moveToFirst();

   }

    public void delete(String show){
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        String [] columns = new String[]{FavoriteEntity.FavoriteEntityFields.COLUMN_SLUG};
        String selection = FavoriteEntity.FavoriteEntityFields.COLUMN_SLUG.
                concat("=").concat("'"+show+"'");
        database.delete(FavoriteEntity.FavoriteEntityFields.TABLE_NAME,selection,null);
    }
}
