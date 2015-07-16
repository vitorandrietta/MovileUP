package com.movile.up.seriestracker.business.assynctask;

import android.content.Context;
import android.os.AsyncTask;

import com.movile.up.seriestracker.business.fetchers.FetchLocalEpisodeDetails;
import com.movile.up.seriestracker.model.models.Episode;

import com.movile.up.seriestracker.interfaces.OnOperationListener;

/**
 * Created by root on 15/07/15.
 */
public  class JsonToEpisodeTask extends AsyncTask<Void,Void,Episode>{

    private  Context context;
    private OnOperationListener<Episode> operation;

    public JsonToEpisodeTask(Context currentContext, OnOperationListener<Episode> operation){
        super();
        this.context = currentContext;
        this.operation = operation;
    }

    @Override
    protected Episode doInBackground(Void... params) {
        FetchLocalEpisodeDetails fetchLocalEpisode = new FetchLocalEpisodeDetails();
        return (fetchLocalEpisode.get(this.context));
    }

    @Override
    protected void onPostExecute(Episode episode) {
        super.onPostExecute(episode);
        this.operation.onOperationSuccess(episode);
    }
}
