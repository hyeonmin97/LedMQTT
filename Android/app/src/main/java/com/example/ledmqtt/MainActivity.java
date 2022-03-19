package com.example.ledmqtt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "mqttmain";

    @Override
    protected void onNewIntent(Intent intent) {
        //인텐트가 만들어져있을때(서비스에서 메인 액티비티 호출)
        Log.d(TAG, "onNewIntent");
        intentProcess(intent);
        finish();
        super.onNewIntent(intent);
    }
    private void intentProcess(Intent intent){
        if(intent != null){
            String message = intent.getStringExtra("mqtt");
            Log.d(TAG, "message : " + message);
            if(message.equals("end"))
                serviceStop();
        }
    }
    private void serviceStop() {
        Intent service = new Intent(this, service.class);
        stopService(service);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        Intent service = new Intent(this, service.class);
        startService(service);

    }
}