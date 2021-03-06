package com.movile.up.seriestracker.business.restclients;

import android.content.Context;
import android.util.Log;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.util.ApiConfiguration;
import com.movile.up.seriestracker.interfaces.callback.presenter.SeasonPresenter;
import com.movile.up.seriestracker.interfaces.rest.SeasonRemoteService;
import com.movile.up.seriestracker.model.models.Episode;
import com.movile.up.seriestracker.model.models.Season;

import java.util.List;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by root on 20/07/15.
 */
public class SeasonRestClient {

    private static final String TAG = SeasonRestClient.class.getSimpleName();


     public static void processSeason(String show, final long season, final SeasonPresenter presenter, Context context) {
        RestAdapter mAdapter = new RestAdapter.Builder().setEndpoint(context.getString(R.string.api_url_base)).build();
        SeasonRemoteService service = mAdapter.create(SeasonRemoteService.class);

     service.getSeasons(show, new Callback<List<Season>>() {
         @Override
         public void success(List<Season> seasons, Response response) {
             int seasonIndex = (int) (season>0 ? (season-1): season);
             presenter.onSeasonCallback(seasons.get(seasonIndex));
         }

         @Override
         public void failure(RetrofitError error) {
             Log.e(TAG, "Error fetching season", error.getCause());
         }
     });


    }

    public static void processSeasonEpisodes(String show, long season, final SeasonPresenter presenter, Context context) {

        RestAdapter mAdapter = new RestAdapter.Builder().setEndpoint(context.getString(R.string.api_url_base)).build();
        SeasonRemoteService service = mAdapter.create(SeasonRemoteService.class);

        service.getSeasonEpisodes(show, season, new Callback<List<Episode>>() {
            @Override
            public void success(List<Episode> episodes, Response response) {
                presenter.onSeasonEpisodesCallback(episodes);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, "Error fetching season episodes", error.getCause());
            }
        });
    }
}
