package com.movile.up.seriestracker.business.adapters.cursoradapters;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.activities.ShowDetailsActivity;
import com.movile.up.seriestracker.database_dbflow.FavoriteEntity;
import com.movile.up.seriestracker.database_dbflow.FavoriteEntity$Table;
import com.movile.up.seriestracker.interfaces.view.FavoriteListItemClick;
import com.movile.up.seriestracker.util.InformationKeys;

//trocar para model
import java.util.ArrayList;
import java.util.List;

/**
 * Created by android on 7/27/15.
 */
public class FavoritesAdapter extends CursorAdapter implements FavoriteListItemClick {

    private Context context;


    public FavoritesAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        this.context = context;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.favorite_list_item, parent, false);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView favoriteShow = (TextView) view.findViewById(R.id.favoriteShow);
        final String showTitle = cursor.getString(cursor.getColumnIndex(FavoriteEntity$Table.TITLE));
        final String showSlug = cursor.getString(cursor.getColumnIndex(FavoriteEntity$Table.SLUG));
        view.setOnClickListener(null);
        favoriteShow.setText(showTitle);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FavoritesAdapter.this.onFavoriteItemClickCallback(new FavoriteEntity(showSlug, showTitle));
            }
        });

    }

    public void update(Cursor favoritesCursor){

        if (this.getCursor() != null) {
            this.getCursor().close();
        }

        this.swapCursor(favoritesCursor);

    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onFavoriteItemClickCallback(FavoriteEntity favoriteEntity) {
        Intent intent = new Intent(this.context, ShowDetailsActivity.class);
        intent.putExtra(InformationKeys.SHOW_SLUG,favoriteEntity.getSlug());
        this.context.startActivity(intent);
    }
}
