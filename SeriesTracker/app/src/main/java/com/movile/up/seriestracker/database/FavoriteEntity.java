package com.movile.up.seriestracker.database;

import android.content.ContentValues;
import android.provider.BaseColumns;

/**
 * Created by root on 26/07/15.
 */
public class FavoriteEntity {
    public static class FavoriteEntityFields implements BaseColumns {
        public static final String TABLE_NAME = "favorite";
        public static final String COLUMN_SLUG = "slug";
        public static final String COLUMN_SLUG_TYPE = "text";

        public static String createSql() {
            String query = "CREATE TABLE IF NOT EXISTS ".concat(TABLE_NAME).concat("( ").
            concat(COLUMN_SLUG.concat(" ").concat(COLUMN_SLUG_TYPE)).concat(" );");
            return query;
        }

        public static String dropSql() {
            String query = "DROP TABLE IF EXISTS ".concat(".").concat(TABLE_NAME);
            return query;
        }
    }

    private String mSlug;

    public FavoriteEntity(String mSlug) {
        this.mSlug = mSlug;
    }

    public ContentValues toContentValues() {
        ContentValues content = new ContentValues();
        content.put(FavoriteEntityFields.COLUMN_SLUG,this.mSlug);
        return  content;
    }


}
