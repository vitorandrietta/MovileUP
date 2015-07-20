package com.movile.up.seriestracker.business.holders;

import android.view.View;
import android.widget.TextView;

import com.movile.up.seriestracker.R;

import org.w3c.dom.Text;

/**
 * Created by root on 19/07/15.
 */
public class EpisodeListItemHolder {

    private final TextView episodeNumber,episodeTitle;

    public EpisodeListItemHolder(View root) {
        this.episodeNumber = (TextView) root.findViewById(R.id.episodeNumberText);
        this.episodeTitle = (TextView) root.findViewById(R.id.episodeTitle);
    }

    public TextView getEpisodeTitle() {
        return episodeTitle;
    }

    public TextView getEpisodeNumber() {
        return episodeNumber;
    }
}
