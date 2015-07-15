package com.movile.up.seriestracker.business;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;

import com.movile.up.seriestracker.model.Episode;

/**
 * Created by root on 15/07/15.
 */
public  class jsonToEpisodeTask extends AsyncTask<Context,Void,Episode>{

    private  Context context;
    private OnOperationListener<Episode> operation;
    public jsonToEpisodeTask(Context currentContext, OnOperationListener<Episode> operation){
        super();
        this.context = currentContext;
        this.operation = operation;

    }

    @Override
    protected Episode doInBackground(Context... params) {
        FetchLocalEpisodeDetails fetchLocalEpisode = new FetchLocalEpisodeDetails();
        return (fetchLocalEpisode.get(this.context));
    }

    @Override
    protected void onPostExecute(Episode episode) {
        super.onPostExecute(episode);
        this.operation.onOperationSuccess(episode);
    }
}
