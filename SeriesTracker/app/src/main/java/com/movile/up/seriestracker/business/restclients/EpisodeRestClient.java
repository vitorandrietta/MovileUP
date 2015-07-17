package com.movile.up.seriestracker.business.restclients;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.business.connection.ConnectionManager;
import com.movile.up.seriestracker.business.presenters.EpisodeDetailsPresenter;
import com.movile.up.seriestracker.interfaces.EpisodeDetailsView;
import com.movile.up.seriestracker.interfaces.EpisodeRemoteService;
import com.movile.up.seriestracker.interfaces.OnOperationListener;
import com.movile.up.seriestracker.model.models.Episode;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by root on 16/07/15.
 */
public class EpisodeRestClient {

    private static final String TAG = EpisodeRestClient.class.getSimpleName();

    public static void processSpecificEpisode(String show, String season, String episode, final EpisodeDetailsPresenter episodePresenter,Context context) {
        RestAdapter mAdapter = new RestAdapter.Builder().setEndpoint(context.getString(R.string.api_url_base)).build();
        EpisodeRemoteService service = mAdapter.create(EpisodeRemoteService.class);
        service.getEpisodeDetails(show, season, episode, new Callback<Episode>() {

            @Override
            public void success(Episode episode, Response response) {
                episodePresenter.onEpisodeDetailsSuccess(episode);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, "Error fetching episode", error.getCause());
            }
        });
    }
}