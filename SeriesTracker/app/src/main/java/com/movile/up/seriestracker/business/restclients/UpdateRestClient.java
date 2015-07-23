package com.movile.up.seriestracker.business.restclients;
import android.content.Context;
import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.business.UpdatesRemoteService;
import com.movile.up.seriestracker.model.models.ShowUpdate;

import retrofit.RestAdapter;

/**
 * Created by android on 7/23/15.
 */
public class UpdateRestClient {
    public ShowUpdate getLastUpdate(Context context) {
        RestAdapter mAdapter = new RestAdapter.Builder().setEndpoint(context.getString(R.string.api_url_updates)).build();
        UpdatesRemoteService service = mAdapter.create(UpdatesRemoteService.class);
        return service.getLatest();
    }
}
