package com.movile.up.seriestracker.business.adapters.listadapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.interfaces.view.SeasonDetailsView;
import com.movile.up.seriestracker.model.models.Episode;

import java.util.List;

/**
 * Created by root on 19/07/15.
 */
public class EpisodeListAdapter extends ArrayAdapter<Episode> {

    private final Context context;
    private List<Episode> episodes;

    private  SeasonDetailsView seasonView;

    @Override
    public int getCount() {
        if (this.episodes == null){
            return 0;
        }

        return this.episodes.size();
    }

    private static class EpisodeListItemHolder {

        private final TextView episodeNumber,episodeTitle;
        private View root;

        public EpisodeListItemHolder(View root) {
            this.episodeNumber = (TextView) root.findViewById(R.id.episodeNumberText);
            this.episodeTitle = (TextView) root.findViewById(R.id.episodeTitle);
            this.root = root;
        }

        public TextView getEpisodeTitle() {
            return episodeTitle;
        }

        public TextView getEpisodeNumber() {
            return episodeNumber;
        }

        public View getRoot(){

            return this.root;
        }
    }

    public EpisodeListAdapter(Context context, int resource, SeasonDetailsView seasonView) {
        super(context, resource);
        this.context = context;
        this.seasonView = seasonView;

    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        EpisodeListItemHolder holder;
        int type = getItemViewType(position);
        if (view == null) {
            int resource = R.layout.episode_list_item_view;
            //if (type == TYPE_TBA) {
            //        resource = R.layout.episode_item_tba;
            //}
            view = LayoutInflater.from(context)
                    .inflate(resource, parent, false);
            holder = new EpisodeListItemHolder(view);
            view.setTag(holder);
        } else {
            holder = (EpisodeListItemHolder) view.getTag();
        }
        populateViewFromHolder(holder, position);

        return view;
    }


    public void notifyListChanged(List<Episode> episodes){
        this.episodes = episodes;
        this.notifyDataSetChanged();
    }

    private void populateViewFromHolder(EpisodeListItemHolder holder, int position) {
        if(this.episodes.isEmpty())
            return;
        holder.getEpisodeNumber().setText("E".concat(Integer.toString(position + 1)));
        holder.getEpisodeTitle().setText(episodes.get(position).title());



    }
}
