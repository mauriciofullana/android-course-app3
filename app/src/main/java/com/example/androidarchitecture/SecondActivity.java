package com.example.androidarchitecture;

import androidx.appcompat.app.AppCompatActivity;

import android.content.IntentFilter;
import android.os.Bundle;

public class SecondActivity extends AppCompatActivity {

    MyReceiver myReceiver;

    public static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        myReceiver = new MyReceiver();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(SMS_RECEIVED);
        registerReceiver(myReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (myReceiver != null) {
            unregisterReceiver(myReceiver);
        }
    }
}