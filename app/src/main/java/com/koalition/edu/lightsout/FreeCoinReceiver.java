package com.koalition.edu.lightsout;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

/**
 * Created by Kingston on 3/15/2016.
 */
public class FreeCoinReceiver extends BroadcastReceiver {

    static int count = 0; // replace this with values from your DB
    final static int MA_PENDINGINTENT = 0;
    final static int NOTIF_ID = 0;

    public FreeCoinReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        PendingIntent pendingIntent
                = PendingIntent.getActivity(context,
                MA_PENDINGINTENT,
                new Intent(context, MainActivity.class),
                PendingIntent.FLAG_UPDATE_CURRENT);


        NotificationCompat.Builder builder
                = new NotificationCompat.Builder(context)
                .setTicker("Ticker text")
                .setContentTitle("Lights Out")
                .setContentText("Get your 100 FREE COINS")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);



        ((NotificationManager)context.getSystemService(Service.NOTIFICATION_SERVICE))
                .notify(NOTIF_ID, builder.build());
    }
}