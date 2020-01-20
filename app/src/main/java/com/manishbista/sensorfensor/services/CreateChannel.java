package com.manishbista.sensorfensor.services;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

public class CreateChannel {

    Context context;
    public static final String CHANNEL_ONE = "Channel1";
    public static final String CHANNEL_TWO = "Channel2";

    public CreateChannel(Context context) {
        this.context = context;
    }

    public void createChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel1 = new NotificationChannel(CHANNEL_ONE,
                    "Channel1", NotificationManager.IMPORTANCE_HIGH);
            channel1.setDescription("New Book Added!");

            NotificationChannel channel2 = new NotificationChannel(CHANNEL_TWO,
                    "Channel2", NotificationManager.IMPORTANCE_LOW);
            channel2.setDescription("New Book Request!");

            NotificationManager manager = context.getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
            manager.createNotificationChannel(channel2);
        }
    }
}
