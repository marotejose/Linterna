package com.example.marote.marotorch;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.view.Menu;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.security.Policy;

public class MainActivity extends Activity implements  OnClickListener {

    private Torch torch;
    private NotificationManager notificationManager;
    private static final int NOTIFICATION_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Encender el flash

        try{
            torch = new Torch();
            torch.on();

            ToggleButton button = (ToggleButton) findViewById(R.id.btn_on_off);
            button.setOnClickListener(this);

            notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            createNotification();
        }
        catch(Exception e){
            Toast.makeText(this,"Linterna en uso",Toast.LENGTH_SHORT).show();
            finish();

            }
    }

    @Override
    public void onClick(View view){
        if (torch.isOn()){
            destroyNotification();
            torch.off();
        }
        else {

            torch.on();
            createNotification();
        }


    }

    // Botón Atras
    @Override
    public void onBackPressed(){
        super.onBackPressed();

        destroyNotification();

        torch.release();
    }

    public void createNotification(){
        Intent intent = new Intent(this,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent, PendingIntent.FLAG_UPDATE_CURRENT );
        Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.linterna)
                .setContentTitle(getResources().getString(R.string.app_name))
                .setContentText(getResources().getString(R.string.notification_text))
                .setOngoing(true)
                .setContentIntent(pendingIntent)
                .build();

        notificationManager.notify(NOTIFICATION_ID, notification);



    }

    public void destroyNotification(){

        notificationManager.cancel(NOTIFICATION_ID);

    }
}
