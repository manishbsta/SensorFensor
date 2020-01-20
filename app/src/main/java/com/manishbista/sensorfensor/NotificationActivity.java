package com.manishbista.sensorfensor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.manishbista.sensorfensor.services.CreateChannel;

import java.nio.channels.Channel;

public class NotificationActivity extends AppCompatActivity {
    NotificationManagerCompat notificationManagerCompat;
    Button btnNotifOne, btnNotifTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        btnNotifOne = findViewById(R.id.btnSendNotifFirst);
        btnNotifTwo = findViewById(R.id.btnSendNotifSecond);

        notificationManagerCompat = NotificationManagerCompat.from(this);
        CreateChannel createChannel = new CreateChannel(this);
        createChannel.createChannel();

        btnNotifOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayNotifOne();
            }
        });

        btnNotifTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayNotifTwo();
            }
        });

    }

    private void displayNotifOne(){
        Notification notification = new NotificationCompat.Builder(this, CreateChannel.CHANNEL_ONE)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Book Submission Successful")
                .setContentText("You have successfully added a new book. Happy BookSwapping!")
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        notificationManagerCompat.notify(1, notification);
    }

    private void displayNotifTwo(){
        Notification notification = new NotificationCompat.Builder(this, CreateChannel.CHANNEL_TWO)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("BookSwap Request")
                .setContentText("You have a new swap request.")
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        notificationManagerCompat.notify(2, notification);
    }
}
