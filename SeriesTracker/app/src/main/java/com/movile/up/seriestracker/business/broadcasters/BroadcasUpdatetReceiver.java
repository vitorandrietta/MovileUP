package com.movile.up.seriestracker.business.broadcasters;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by root on 23/07/15.
 */
public class BroadcasUpdatetReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String mensagem = intent.getStringExtra("mensagem");
    }
}
