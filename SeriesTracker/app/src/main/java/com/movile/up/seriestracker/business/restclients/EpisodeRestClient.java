package com.movile.up.seriestracker.business.restclients;

import android.content.Context;
import android.util.Log;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.interfaces.callback.restClient.EpisodeClient;
import com.movile.up.seriestracker.interfaces.callback.presenter.EpisodePresenter;
import com.movile.up.seriestracker.interfaces.rest.EpisodeRemoteService;
import com.movile.up.seriestracker.model.models.Episode;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by root on 16/07/15.
 */
public  class EpisodeRestClient  {

    private static final String TAG = EpisodeRestClient.class.getSimpleName();


    public static void processEpisode(String show,long season, long episode, final EpisodePresenter presenter, Context context) {



        RestAdapter mAdapter = new RestAdapter.Builder().setEndpoint(context.getString(R.string.api_url_base)).build();
         EpisodeRemoteService service = mAdapter.create(EpisodeRemoteService.class);

        service.getEpisodeDetails(show, season, episode, new Callback<Episode>() {

            @Override
            public void success(Episode episode, Response response) {
                presenter.onEpisodeCallback(episode);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, "Error fetching episode", error.getCause());
            }

        });
    }


}