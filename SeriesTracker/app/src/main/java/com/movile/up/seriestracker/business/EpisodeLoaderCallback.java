package com.movile.up.seriestracker.business;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;

import com.movile.up.seriestracker.interfaces.OnOperationListener;
import com.movile.up.seriestracker.model.models.Episode;

/**
 * Created by android on 7/16/15.
 */
public class EpisodeLoaderCallback implements LoaderManager.LoaderCallbacks<Episode> {

     private OnOperationListener<Episode> episodeListener;
     private  Context context;

    public EpisodeLoaderCallback(OnOperationListener<Episode> operation,Context context){
        this.episodeListener = operation;
        this.context = context;
    }

    @Override
    public Loader<Episode> onCreateLoader(int id, Bundle args) {
        return new EpisodeLoader(this.context,this.episodeListener);
    }

    @Override
    public void onLoadFinished(Loader<Episode> loader, Episode data) {

    }

    @Override
    public void onLoaderReset(Loader<Episode> loader) {

    }
}
