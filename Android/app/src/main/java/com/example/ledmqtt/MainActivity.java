package com.example.ledmqtt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            MqttClient client = new MqttClient("tcp://192.168.0.75:1883", MqttClient.generateClientId(),null);
            client.connect();
            client.publish("led", new MqttMessage("ON".getBytes()));
        } catch (MqttException e) {
            e.printStackTrace();
        }
        finish();
    }
}