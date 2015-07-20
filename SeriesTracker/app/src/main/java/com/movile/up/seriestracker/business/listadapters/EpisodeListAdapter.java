package com.movile.up.seriestracker.business.listadapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.business.holders.EpisodeListItemHolder;
import com.movile.up.seriestracker.interfaces.view.SeasonDetailsView;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by root on 19/07/15.
 */
public class EpisodeListAdapter extends ArrayAdapter<String> {

    private final Context context;
    private ArrayList<String> episodeTitles;
    private  SeasonDetailsView seasonView;

    public EpisodeListAdapter(Context context, int resource, SeasonDetailsView seasonView,ArrayList<String> episodeTitleList) {
        super(context, resource);
        this.context = context;
        this.seasonView = seasonView;
        this.episodeTitles = episodeTitleList;
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
        populateViewFromHolder(holder, position, type);

        return view;
    }

    private void populateViewFromHolder(EpisodeListItemHolder holder, int position, int type) {
        //type verification
        this.seasonView.displaySeasonEpisodes(holder,episodeTitles,position);
    }
}
