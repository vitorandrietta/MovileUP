package com.movile.up.seriestracker.business.restclients;

import android.content.Context;
import android.util.Log;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.util.ApiConfiguration;
import com.movile.up.seriestracker.interfaces.callback.presenter.ShowPresenter;
import com.movile.up.seriestracker.interfaces.callback.presenter.ShowsPresenter;
import com.movile.up.seriestracker.interfaces.rest.ShowRemoteService;
import com.movile.up.seriestracker.model.models.Show;

import java.util.List;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by root on 22/07/15.
 */
public class ShowRestClient  {

    private static final String TAG = ShowRestClient.class.getSimpleName();


    public static void processShow(String show, final ShowPresenter presenter, Context context) {
        RestAdapter mAdapter = new RestAdapter.Builder().setEndpoint(context.getString(R.string.api_url_base)).build();
        ShowRemoteService service = mAdapter.create(ShowRemoteService.class);

        service.getShowDetails(show, new Callback<Show>() {
            @Override
            public void success(Show show, Response response) {
                presenter.onShowCallback(show);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, "Error fetching show", error.getCause());
            }
        });
    }


    public static void processShows(final ShowsPresenter presenter, Context context) {

        RestAdapter mAdapter = new RestAdapter.Builder().setEndpoint(context.getString(R.string.api_url_base)).build();
        ShowRemoteService service = mAdapter.create(ShowRemoteService.class);

        service.getShows(new Callback<List<Show>>() {
            @Override
            public void success(List<Show> shows, Response response) {
                presenter.onShowsCallback(shows);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, "Error fetching shows", error.getCause());
            }
        });
    }


}
