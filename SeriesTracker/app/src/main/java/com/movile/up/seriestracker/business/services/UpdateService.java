package com.movile.up.seriestracker.business.services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.movile.up.seriestracker.business.restclients.UpdateRestClient;
import com.movile.up.seriestracker.util.FormatUtil;
import com.movile.up.seriestracker.util.InformationKeys;
import com.movile.up.seriestracker.model.models.ShowUpdate;
import com.movile.up.seriestracker.util.SharedPreferencesKeys;

import java.text.Normalizer;
import java.util.Date;

/**
 * Created by android on 7/23/15.
 */
public class UpdateService extends IntentService {

    private static final String TAG = UpdateService.class.getSimpleName();
    private UpdateRestClient client;


    public UpdateService() {
        super(InformationKeys.UPDATE_SERVICE);

    }

    @Override
    protected void onHandleIntent(Intent intent) {
       SharedPreferences preferences = getBaseContext().getSharedPreferences
               (SharedPreferencesKeys.FILE_NAME, Context.MODE_PRIVATE);

        ShowUpdate showUpdate = client.getLastUpdate(getBaseContext());
        String lastUpdateStr = preferences.getString(SharedPreferencesKeys.LAST_UPDATE, null);

        if(lastUpdateStr != null){
            Date currentUpdateDate = FormatUtil.formatDate(showUpdate.date());
            Date lastUpdateDate = FormatUtil.formatDate(lastUpdateStr);
            if(currentUpdateDate.compareTo(lastUpdateDate)<=0){
                return;
            }
        }

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(SharedPreferencesKeys.LAST_UPDATE,showUpdate.date());
        editor.commit();
        Intent intentCall = new Intent("com.movile.up.seriestracker.action.SHOW_UPDATE");
        intentCall.putExtra(InformationKeys.SERVICE_MESSAGE,showUpdate);
        sendBroadcast(intentCall);
    }
}
