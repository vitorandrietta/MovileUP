package com.movile.up.seriestracker.business.adapters.cursoradapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.database_dbflow.FavoriteEntity;
import com.movile.up.seriestracker.database_dbflow.FavoriteEntity$Table;

//trocar para model
import java.util.List;

/**
 * Created by android on 7/27/15.
 */
public class FavoritesAdapter extends CursorAdapter {


    public FavoritesAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.favorite_list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView favoriteShow = (TextView) view.findViewById(R.id.favoriteShow);
        cursor.getString(cursor.getColumnIndex(FavoriteEntity$Table.SLUG));
    }


}
