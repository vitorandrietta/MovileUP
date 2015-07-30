package com.movile.up.seriestracker.business.services.update;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.GcmTaskService;
import com.google.android.gms.gcm.TaskParams;
import com.movile.up.seriestracker.business.restclients.UpdateRestClient;
import com.movile.up.seriestracker.util.FormatUtil;
import com.movile.up.seriestracker.util.InformationKeys;
import com.movile.up.seriestracker.model.models.ShowUpdate;
import com.movile.up.seriestracker.util.SharedPreferencesKeys;

import java.util.Date;

/**
 * Created by android on 7/23/15.
 */
public class UpdateService extends GcmTaskService {

    private static final String TAG = UpdateService.class.getSimpleName();
    private UpdateRestClient client;


    @Override
    public int onRunTask(TaskParams taskParams) {
        try {

            SharedPreferences preferences = getBaseContext().getSharedPreferences
                    (SharedPreferencesKeys.FILE_NAME, Context.MODE_PRIVATE);

            ShowUpdate showUpdate = client.getLastUpdate(getBaseContext());
            String lastUpdateStr = preferences.getString(SharedPreferencesKeys.LAST_UPDATE, null);

            if (lastUpdateStr != null) {
                Date currentUpdateDate = FormatUtil.formatDate(showUpdate.date());
                Date lastUpdateDate = FormatUtil.formatDate(lastUpdateStr);
                if (currentUpdateDate.compareTo(lastUpdateDate) <= 0) {
                    return GcmNetworkManager.RESULT_SUCCESS;
                }
            }

            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(SharedPreferencesKeys.LAST_UPDATE, showUpdate.date());
            editor.commit();
            Intent intentCall = new Intent("com.movile.up.seriestracker.action.SHOW_UPDATE");
            intentCall.putExtra(InformationKeys.SERVICE_MESSAGE, showUpdate);
            sendBroadcast(intentCall);
        } catch (Exception ex) {
            Log.d(TAG, ex.getMessage());
            return GcmNetworkManager.RESULT_FAILURE;

        }
        return GcmNetworkManager.RESULT_SUCCESS;
    }


}
