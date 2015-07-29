package com.movile.up.seriestracker.business.restclients;

import android.content.Context;
import android.util.Log;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.util.ApiConfiguration;
import com.movile.up.seriestracker.interfaces.callback.presenter.FragmentSeasonPresenter;
import com.movile.up.seriestracker.interfaces.callback.restClient.SeasonFragmentClient;
import com.movile.up.seriestracker.interfaces.rest.SeasonRemoteService;
import com.movile.up.seriestracker.model.models.Season;

import java.util.List;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by android on 7/22/15.
 */
public class SeasonFragmentRestClient  {
    private  static final String TAG = SeasonFragmentClient.class.getSimpleName();

    public static void processSeasons(String show, final FragmentSeasonPresenter presenter, Context context) {
        RestAdapter mAdapter = new RestAdapter.Builder().setEndpoint(context.getString(R.string.api_url_base)).build();
        SeasonRemoteService service = mAdapter.create(SeasonRemoteService.class);

        service.getSeasons(show, new Callback<List<Season>>() {
            @Override
            public void success(List<Season> seasons, Response response) {
                presenter.onSeasonFragmentCallback(seasons);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, "Error fetching season", error.getCause());
            }
        });
    }
}
