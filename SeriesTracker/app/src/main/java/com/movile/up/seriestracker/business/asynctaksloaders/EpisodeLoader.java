package com.movile.up.seriestracker.business.asynctaksloaders;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.movile.up.seriestracker.business.fetchers.FetchRemoteEpisodeDetails;
import com.movile.up.seriestracker.model.models.Episode;

/**
 * Created by android on 7/16/15.
 */
public class EpisodeLoader extends AsyncTaskLoader<Episode> {

    Context context;

    public EpisodeLoader(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public Episode loadInBackground() {
        return new FetchRemoteEpisodeDetails().get(context);
    }
}
