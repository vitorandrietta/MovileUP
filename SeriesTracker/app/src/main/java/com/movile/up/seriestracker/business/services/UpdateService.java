package com.movile.up.seriestracker.business.services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.movile.up.seriestracker.business.restclients.UpdateRestClient;
import com.movile.up.seriestracker.configuration.InformationKeys;
import com.movile.up.seriestracker.model.models.ShowUpdate;

/**
 * Created by android on 7/23/15.
 */
public class UpdateService extends IntentService {

    private static final String TAG = UpdateService.class.getSimpleName();
    private UpdateRestClient client;


    public UpdateService() {
        super("Test service");
        client = new UpdateRestClient();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        ShowUpdate showUpdateMessage = client.getLastUpdate(getBaseContext());
        Intent intentCall = new Intent("com.movile.up.seriestracker.action.SHOW_UPDATE");
        intentCall.putExtra(InformationKeys.SERVICE_MESSAGE,showUpdateMessage);
        sendBroadcast(intentCall);
    }
}
