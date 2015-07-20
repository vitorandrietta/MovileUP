package com.movile.up.seriestracker.business.restclients;

import android.content.Context;
import android.util.Log;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.interfaces.callback.SeasonClient;
import com.movile.up.seriestracker.interfaces.callback.SeasonPresenter;
import com.movile.up.seriestracker.interfaces.rest.EpisodeRemoteService;
import com.movile.up.seriestracker.interfaces.rest.SeasonRemoteService;
import com.movile.up.seriestracker.model.models.Episode;
import com.movile.up.seriestracker.model.models.Season;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by root on 20/07/15.
 */
public class SeasonRestClient implements SeasonClient {

    private static final String TAG = EpisodeRestClient.class.getSimpleName();

    @Override
     public void processSeason(String show, long season, final SeasonPresenter presenter, Context context) {
        RestAdapter mAdapter = new RestAdapter.Builder().setEndpoint(context.getString(R.string.api_url_base)).build();
        SeasonRemoteService service = mAdapter.create(SeasonRemoteService.class);

        service.getSeasonDetails(show, season, new Callback<Season>() {


            @Override
            public void success(Season season, Response response) {
                presenter.onSeasonCallback(season);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, "Error fetching season", error.getCause());
            }
        });

    }
}
