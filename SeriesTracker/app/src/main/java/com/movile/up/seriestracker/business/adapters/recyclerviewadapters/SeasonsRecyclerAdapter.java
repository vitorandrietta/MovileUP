package com.movile.up.seriestracker.business.adapters.recyclerviewadapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.activities.SeasonDetailsActivity;
import com.movile.up.seriestracker.configuration.ImageTypes;
import com.movile.up.seriestracker.configuration.InformationKeys;
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
    private  String show;

    public SeasonsRecyclerAdapter(Context context, int layout,String show){
        this.mContext = context;
        this.mLayout = layout;
        this.show = show;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)  {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(mLayout, viewGroup, false);
        final ViewHolder viewHolder = new ViewHolder(view,this.mContext,this.show);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewHolder.onSeasonClicked(view);
            }
        });
        return  viewHolder;
     }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Season season = this.seasonList.get(i);
        viewHolder.seasonNumberText.setText("Season ".concat(Long.toString(season.number())));
        viewHolder.seasonsEpisodesNumberText.setText((Long.toString(season.episodeCount())).concat(" Episodes"));

        Glide.with(mContext).
                load(season.images().poster().get(ImageTypes.IMAGE_FULL)).
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

        public TextView seasonNumberText;
        public TextView seasonsEpisodesNumberText;
        public ImageView seasonsThumb;
        public Context context;
        public String show;

        public ViewHolder(View itemView,Context context,String show) {
            super(itemView);
            this.seasonNumberText = (TextView) itemView.findViewById(R.id.seasonNumber);
            this.seasonsEpisodesNumberText = (TextView) itemView.findViewById(R.id.episodesNumber);
            this.seasonsThumb = (ImageView) itemView.findViewById(R.id.seasonMainImage);
            this.show= show;
            this.context=context;
        }

        @Override
        public void onSeasonClicked(View root) {
            Intent intent =  new Intent(this.context, SeasonDetailsActivity.class);
            String seasonStr = this.seasonNumberText.getText().toString();
            Long seasonNumber = Long.parseLong(seasonStr.substring(seasonStr.indexOf(" ") + 1));
            intent.putExtra(InformationKeys.SEASON,seasonNumber);
            intent.putExtra(InformationKeys.SHOW,this.show);
            context.startActivity(intent);
        }
    }

}
