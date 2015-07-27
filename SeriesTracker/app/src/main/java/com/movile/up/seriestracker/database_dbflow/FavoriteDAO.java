package com.movile.up.seriestracker.database_dbflow;

import android.database.Cursor;

import com.movile.up.seriestracker.model.models.Favorite;
import com.raizlabs.android.dbflow.sql.builder.Condition;
import com.raizlabs.android.dbflow.sql.language.Delete;
import com.raizlabs.android.dbflow.sql.language.Select;

/**
 * Created by root on 26/07/15.
 */
public class FavoriteDAO {

    public static void insert(FavoriteEntity favoriteEntity){
           favoriteEntity.save();
    }

    public static void delete(FavoriteEntity favoriteEntity){
        new Delete()
                .from(FavoriteEntity.class)
                .where(Condition.column(FavoriteEntity$Table.SLUG).eq(favoriteEntity.getSlug()))
                .queryClose();
    }

    public static  FavoriteEntity getFavorite(FavoriteEntity favoriteEntity){
     FavoriteEntity entity = new Select()
        .from(FavoriteEntity.class)
        .where(Condition.column(FavoriteEntity$Table.SLUG).eq(favoriteEntity.getSlug()))
        .querySingle();
        return  entity;
    }

}
