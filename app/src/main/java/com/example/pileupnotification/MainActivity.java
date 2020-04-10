package com.example.pileupnotification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "foo";
    FirebaseDatabase database;
    DatabaseReference rootRef,NotificationRef;
    SharedPreferences sharedPreferences;
    int count;
    TextView countTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         database = FirebaseDatabase.getInstance();
         rootRef = database.getReference();
         countTV = findViewById(R.id.count);

         sharedPreferences = getApplicationContext().getSharedPreferences("MyPref",0);

         countTV.setText(String.valueOf(sharedPreferences.getInt("count",0)));

        if (checkpermission()) {
            LocalBroadcastManager.getInstance(this).registerReceiver(onNotice, new IntentFilter("Msg"));
        } else {
            //service is not enabled try to enabled by calling...
            getApplicationContext().startActivity(new Intent(
                    "android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"));
        }
    }

    private BroadcastReceiver onNotice = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

        }
    };

    private Boolean checkpermission() {
        if (Settings.Secure.getString(this.getContentResolver(), "enabled_notification_listeners").contains(getApplicationContext().getPackageName())) {
            return true;
            //service is enabled do something
        } else {
            return false;
        }
    }


}
