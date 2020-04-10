package com.example.pileupnotification;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.widget.Toast;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NotificationService extends NotificationListenerService {

    Context context;
    notifications Notifications ;
    FirebaseDatabase database;
    DatabaseReference rootRef;
    SharedPreferences sharedPreferences;
    @Override

    public void onCreate(){
        super.onCreate();
        context = getApplicationContext();
        database = FirebaseDatabase.getInstance();
        rootRef = database.getReference();
        sharedPreferences = getApplicationContext().getSharedPreferences("MyPref",0);
    }
    @Override

    public void onNotificationPosted(StatusBarNotification sbn){

        String pack = sbn.getPackageName();
        Bundle extras = sbn.getNotification().extras;
        int id = sbn.getUid();
        String title = extras.getString("android.title");
        String text = extras.getString("android.text");

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String today = formatter.format(date);
        //for ignoring common stuff



        if(checkforCommon(pack)){
            Notifications = new notifications(id,pack,title,text,today);
            rootRef.child(String.valueOf(sbn.getPostTime())).setValue(Notifications);
        }
        //need to avoid duplicate storage
        Intent msgrcv = new Intent("Msg");
        msgrcv.putExtra("package", pack);
        msgrcv.putExtra("title", title);
        msgrcv.putExtra("text", text);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        int c = sharedPreferences.getInt("count",0);
        editor.putInt("count",c+1);
        editor.commit();

        LocalBroadcastManager.getInstance(context).sendBroadcast(msgrcv);
    }

    private boolean checkforCommon(String pack) {
        if(pack.contains("com.android.providers.downloads")||pack.contains("huawei")){
            return false;
        }else return true;
    }

    @Override

    public void onNotificationRemoved(StatusBarNotification sbn){
        Log.i("Msg","Notification Removed");
        Toast.makeText(context, "Notification Removed", Toast.LENGTH_SHORT).show();
    }

}
