package com.movile.up.seriestracker.business.presenters;

import android.content.Context;
import android.util.Log;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.interfaces.callback.EpisodeDetailsCallback;
import com.movile.up.seriestracker.interfaces.rest.EpisodeRemoteService;
import com.movile.up.seriestracker.interfaces.view.EpisodeDetailsView;
import com.movile.up.seriestracker.model.models.Episode;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by root on 16/07/15.
 */
public class EpisodeDetailsPresenter implements EpisodeDetailsCallback {

    private EpisodeDetailsView mView;
    private Context context;
    private static final String TAG = EpisodeDetailsCallback.class.getSimpleName();

    public EpisodeDetailsPresenter(EpisodeDetailsView mView,Context context){
        this.mView = mView;
        this.context = context;
    }

    public void onEpisodeDetailsSuccess(Episode episode) {
        mView.displayEpisode(episode);
    }


    @Override
    public void presentEpisode(String show, Long season, Long episode) {
        RestAdapter mAdapter = new RestAdapter.Builder().setEndpoint(context.getString(R.string.api_url_base)).build();
        EpisodeRemoteService service = mAdapter.create(EpisodeRemoteService.class);
        service.getEpisodeDetails(show, season, episode, new Callback<Episode>() {

            @Override
            public void success(Episode episode, Response response) {
                onEpisodeDetailsSuccess(episode);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, "Error fetching episode", error.getCause());
            }
        });
    }
}
