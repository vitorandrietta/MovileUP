package com.movile.up.seriestracker.database_dbflow;

import android.database.Cursor;

import com.raizlabs.android.dbflow.sql.builder.Condition;
import com.raizlabs.android.dbflow.sql.language.Delete;
import com.raizlabs.android.dbflow.sql.language.Select;

/**
 * Created by root on 26/07/15.
 */
public class FavoriteDAO {

    public static void insert(String show){
        FavoriteEntity entity = new FavoriteEntity();
        entity.slug = show;

        entity.save();
    }

    public static void delete(String show){

        new Delete()
                .from(FavoriteEntity.class)
                .where(Condition.column(FavoriteEntity$Table.SLUG).eq(show))
                .queryClose();
    }

    public static  boolean isFavorite(String show){
        Cursor cursor = new Select().from(FavoriteEntity.class).
        where(Condition.column(FavoriteEntity$Table.SLUG).eq(show)).
        queryCursorList().getCursor();
        boolean favorite = cursor.moveToFirst();
        cursor.close();
        return  favorite;
    }

}
