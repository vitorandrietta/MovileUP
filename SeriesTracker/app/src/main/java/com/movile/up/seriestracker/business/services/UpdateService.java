package com.movile.up.seriestracker.business.services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.movile.up.seriestracker.business.restclients.UpdateRestClient;

/**
 * Created by android on 7/23/15.
 */
public class UpdateService extends IntentService {

    private static final String TAG = UpdateService.class.getSimpleName();
    private UpdateRestClient client;


    public UpdateService(String name) {
        super(name);
        client = new UpdateRestClient();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG,client.getLastUpdate(getBaseContext()).message());
    }
}
