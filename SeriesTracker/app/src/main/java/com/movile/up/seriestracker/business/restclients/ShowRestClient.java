package com.movile.up.seriestracker.business.restclients;

import android.content.Context;
import android.util.Log;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.interfaces.callback.presenter.ShowPresenter;
import com.movile.up.seriestracker.interfaces.callback.restClient.ShowClient;
import com.movile.up.seriestracker.interfaces.rest.SeasonRemoteService;
import com.movile.up.seriestracker.interfaces.rest.ShowRemoteService;
import com.movile.up.seriestracker.model.models.Show;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by root on 22/07/15.
 */
public class ShowRestClient implements ShowClient {

    private static final String TAG = ShowRestClient.class.getSimpleName();

    @Override
    public void processShow(String show, final ShowPresenter presenter, Context context) {
        RestAdapter mAdapter = new RestAdapter.Builder().setEndpoint(context.getString(R.string.api_url_base)).build();
        ShowRemoteService service = mAdapter.create(ShowRemoteService.class);

        service.getShowDetails(show, new Callback<Show>() {
            @Override
            public void success(Show show, Response response) {
                presenter.onShowCallback(show);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, "Error fetching season", error.getCause());
            }
        });
    }


}
