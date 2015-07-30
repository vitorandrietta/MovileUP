package com.movile.up.seriestracker.business.broadcasters;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.OneoffTask;
import com.google.android.gms.gcm.Task;
import com.movile.up.seriestracker.business.services.gcm.GcmUpdateTrigger;

/**
 * Created by android on 7/30/15.
 */
public class BootUpdateBroadcast extends BroadcastReceiver {

    public final static String TAG = BroadcastReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {


    }
}
