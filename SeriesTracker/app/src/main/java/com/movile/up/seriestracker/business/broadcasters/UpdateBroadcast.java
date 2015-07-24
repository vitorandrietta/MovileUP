package com.movile.up.seriestracker.business.broadcasters;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import com.movile.up.seriestracker.business.services.UpdateService;

/**
 * Created by android on 7/24/15.
 */
public class UpdateBroadcast extends BroadcastUpdateReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, new Intent(context, UpdateService.class), 0);
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        manager.setRepeating(AlarmManager.RTC_WAKEUP,0, 1000, pendingIntent);
    }
}
