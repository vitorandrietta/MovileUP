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
import com.movile.up.seriestracker.database_dbflow.FavoriteEntity$Adapter;
import com.movile.up.seriestracker.database_dbflow.FavoriteEntity$Table;
import com.movile.up.seriestracker.interfaces.view.FavoriteListItemClick;
import com.movile.up.seriestracker.model.models.Favorite;
import com.movile.up.seriestracker.util.InformationKeys;

//trocar para model
import java.util.ArrayList;
import java.util.List;

/**
 * Created by android on 7/27/15.
 */
public class FavoritesAdapter extends CursorAdapter implements FavoriteListItemClick {

    private Context context;
    private FavoriteEntity$Adapter favoriteEntityAdapter;

    public FavoritesAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        this.context = context;
        this.favoriteEntityAdapter = new FavoriteEntity$Adapter();
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.favorite_list_item, parent, false);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder holder = new ViewHolder(view);
        view.setTag(holder);
        final FavoriteEntity currentEntity = favoriteEntityAdapter.loadFromCursor(cursor);
        view.setOnClickListener(null);
        holder.showTitleText.setText(currentEntity.getTitle());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FavoritesAdapter.this.onFavoriteItemClickCallback(currentEntity);
            }
        });

    }

    public void update(Cursor favoritesCursor){

        if (this.getCursor() != null) {
            this.getCursor().close();
        }

        this.swapCursor(favoritesCursor);

    }

    private class ViewHolder{

        View root;
        TextView showTitleText;

        public ViewHolder(View root){
            this.root = root;
            this.showTitleText = (TextView) root.findViewById(R.id.favoriteShow);
        }

        public View getRoot() {
            return root;
        }

        public TextView getShowTitleText() {
            return showTitleText;
        }
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
