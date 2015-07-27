package com.movile.up.seriestracker.business.broadcasters;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.activities.ShowDetailsActivity;
import com.movile.up.seriestracker.util.InformationKeys;
import com.movile.up.seriestracker.model.models.ShowUpdate;

/**
 * Created by root on 23/07/15.
 */
public class BroadcastUpdateReceiver extends BroadcastReceiver {

    private  static final String TAG = BroadcastReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        ShowUpdate showUpdateMessage = (ShowUpdate) intent.getSerializableExtra(InformationKeys.SERVICE_MESSAGE);

        Intent showItent = new Intent(context, ShowDetailsActivity.class);
        showItent.putExtra(InformationKeys.SHOW_SLUG, showUpdateMessage.show());
        showItent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(ShowDetailsActivity.class);
        stackBuilder.addNextIntent(showItent);

        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle(showUpdateMessage.title())
                .setContentText(showUpdateMessage.message())
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(showUpdateMessage.message()));

        Notification notification = builder.build();

        NotificationManager manager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, notification);




    }
}
