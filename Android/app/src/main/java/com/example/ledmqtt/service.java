package com.example.ledmqtt;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class service extends Service {
    MqttClient client;
    @Override
    public void onCreate() {
        super.onCreate();
        try {
            client = new MqttClient("tcp://192.168.0.75:1883", MqttClient.generateClientId(),null);
            client.connect();
            client.publish("led", new MqttMessage("ON".getBytes()));
            client.disconnect();
            Log.d("service_", "client disconnect");
        } catch (MqttException e) {
            e.printStackTrace();
        } finally {
            Intent main = new Intent(getApplicationContext(), MainActivity.class);
            main.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            main.putExtra("mqtt", "end");
            startActivity(main);
        }


    }

    @Override
    public void onDestroy() {
        Log.d("service_", "onDestroy");
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
