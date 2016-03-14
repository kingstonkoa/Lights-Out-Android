package com.koalition.edu.lightsout;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

/**
 * Created by John Israel on 3/15/2016.
 */
public class FreeCoinReceiver extends BroadcastReceiver {

    static final int NOTIF_ID = 1;
    final static int MA_PENDINGINTENT = 0;
    static int count = 0;

    @Override
    public void onReceive(Context context, Intent intent) {
        count++;

        Intent maIntent = new Intent();
        maIntent.setClass(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, MA_PENDINGINTENT, maIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder notificationBuilder
                = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("I AM A TITLE")
                .setContentText("YOUR TROOPS ARE READY FOR BATTLE")
                .setTicker("NOTICE ME SENPAI")
                .setNumber(count)
                .setContentIntent(pendingIntent);

        // submit our notif to notifmanager
        NotificationManager notificationManager
                = (NotificationManager) context.getSystemService(Service.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIF_ID, notificationBuilder.build());
    }
}
