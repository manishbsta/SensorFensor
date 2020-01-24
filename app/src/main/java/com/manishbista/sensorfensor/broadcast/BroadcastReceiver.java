package com.manishbista.sensorfensor.broadcast;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.manishbista.sensorfensor.R;
import com.manishbista.sensorfensor.services.CreateChannel;

public class BroadcastReceiver extends android.content.BroadcastReceiver {
    NotificationManagerCompat notificationManagerCompat;
    Context context;

    public BroadcastReceiver(Context context) {
        this.context = context;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        boolean connectivity;
        notificationManagerCompat = NotificationManagerCompat.from(context);
        if(ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())){
            connectivity = intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false);

            if(connectivity){
                Toast.makeText(context, "connected", Toast.LENGTH_SHORT).show();
                displayNotificationOne();
            } else {
                Toast.makeText(context, "disconnected", Toast.LENGTH_SHORT).show();
                displayNotificationTwo();
            }
        }
    }

    public void displayNotificationOne(){
        Notification notification = new NotificationCompat.Builder(context, CreateChannel.CHANNEL_ONE)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Connected")
                .setContentText("Wifi Connected")
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManagerCompat.notify(3, notification);
    }

    public void displayNotificationTwo(){
        Notification notification = new NotificationCompat.Builder(context, CreateChannel.CHANNEL_ONE)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Disconnected")
                .setContentText("Wifi disConnected")
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManagerCompat.notify(4, notification);
    }
}
