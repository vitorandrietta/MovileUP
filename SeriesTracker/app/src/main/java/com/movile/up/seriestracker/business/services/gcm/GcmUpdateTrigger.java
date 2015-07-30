package com.movile.up.seriestracker.business.services.gcm;

import android.util.Log;

import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.GcmTaskService;
import com.google.android.gms.gcm.OneoffTask;
import com.google.android.gms.gcm.PeriodicTask;
import com.google.android.gms.gcm.Task;
import com.google.android.gms.gcm.TaskParams;
import com.movile.up.seriestracker.business.services.update.UpdateService;

import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by android on 7/30/15.
 */


public class GcmUpdateTrigger extends GcmTaskService {

    public static final String TAG = GcmUpdateTrigger.class.getSimpleName();

    @Override

    public int onRunTask(TaskParams taskParams) {
       PeriodicTask updateTask = null;

        try {
            updateTask = new PeriodicTask.Builder()
                    .setService(UpdateService.class)
                    .setTag(TAG)
                    .setFlex(2)
                    .setPeriod(5)//trocar para um dia
                    .setRequiresCharging(false)
                    .setRequiredNetwork(Task.NETWORK_STATE_CONNECTED).build();

        } catch (Exception ex) {
            Log.d(TAG, ex.getMessage());
            return GcmNetworkManager.RESULT_FAILURE;
        }

        GcmNetworkManager.getInstance(this).schedule(updateTask);
        return GcmNetworkManager.RESULT_SUCCESS;

    }
}
