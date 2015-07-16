package com.movile.up.seriestracker.business;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.movile.up.seriestracker.interfaces.OnOperationListener;
import com.movile.up.seriestracker.model.models.Episode;

/**
 * Created by android on 7/16/15.
 */
public class EpisodeLoader extends AsyncTaskLoader<Episode> {

    OnOperationListener<Episode> operation;
    Context context;

    public EpisodeLoader(Context context,OnOperationListener<Episode> operation) {
        super(context);
        this.operation = operation;
        this.context = context;
    }

    @Override
    public Episode loadInBackground() {
        return new FetchEpisodeRemote().get(context);
    }
}
