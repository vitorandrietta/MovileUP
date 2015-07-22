package com.movile.up.seriestracker.business.recyclerviewadapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.activities.SeasonDetailsActivity;
import com.movile.up.seriestracker.interfaces.view.SeasonFragmentClick;
import com.movile.up.seriestracker.model.models.Season;

import java.util.List;

/**
 * Created by android on 7/22/15.
 */
public class SeasonsRecyclerAdapter extends RecyclerView.Adapter<SeasonsRecyclerAdapter.ViewHolder>{

    private  List<Season> seasonList;
    private int mLayout;
    private Context mContext;

    public SeasonsRecyclerAdapter(Context context, int layout){
        this.mContext = context;
        this.mLayout = layout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)  {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(mLayout, viewGroup, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return new ViewHolder(view,this.mContext);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Season season = this.seasonList.get(i);
        viewHolder.SeasonNumberText.setText("Season: ".concat(Long.toString(season.number())));
        viewHolder.SeasonsEpisodesNumberText.setText("Episodes: ".concat(Long.toString(season.episodeCount())));

        Glide.with(mContext).
                load(season.images().poster().get("full")).
                centerCrop().
                into(viewHolder.seasonsThumb);
    }

    public void updateContents(List<Season> contents) {
        seasonList = contents;
        notifyDataSetChanged();
    }




    @Override
    public int getItemCount() {
        return seasonList != null ? seasonList.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements SeasonFragmentClick {

        public TextView SeasonNumberText;
        public TextView SeasonsEpisodesNumberText;
        public ImageView seasonsThumb;
        public Context context;

        public ViewHolder(View itemView,Context context) {
            super(itemView);
            this.SeasonNumberText = (TextView) itemView.findViewById(R.id.seasonNumber);
            this.SeasonsEpisodesNumberText = (TextView) itemView.findViewById(R.id.episodesNumber);
            this.seasonsThumb = (ImageView) itemView.findViewById(R.id.seasonMainImage);
        }

        @Override
        public void onSeasonClicked(View root) {
            Intent intent =  new Intent(this.context, SeasonDetailsActivity.class);
            //intent.putExtra()
        }
    }

}
