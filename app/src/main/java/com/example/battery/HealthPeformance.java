package com.example.battery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class HealthPeformance extends AppCompatActivity {
    TextView textview;
    Button button;
    IntentFilter intentFilter;
    int deviceHealth;
    String currentBatteryHealth = "Battery Health";
    int batteryLevel;
    RatingBar r1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_peformance);

        button = findViewById(R.id.button);
        textview = findViewById(R.id.textView);
        r1=findViewById(R.id.ratingBar);

        intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        button.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                HealthPeformance.this.registerReceiver(broadcastreceiver, intentFilter);
              //  textview.setVisibility(1);
            }
        });
    }

    private BroadcastReceiver broadcastreceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            deviceHealth = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, 0);
             r1.setIsIndicator(true);
            if (deviceHealth == BatteryManager.BATTERY_HEALTH_COLD) {

                textview.setText(currentBatteryHealth + " = Cold");
r1.setRating(1);
            }

            if (deviceHealth == BatteryManager.BATTERY_HEALTH_DEAD) {

                textview.setText(currentBatteryHealth + " = Dead");
                r1.setRating(0);

            }

            if (deviceHealth == BatteryManager.BATTERY_HEALTH_GOOD) {

                textview.setText(currentBatteryHealth + " = Good");
                r1.setRating(5);
            }

            if (deviceHealth == BatteryManager.BATTERY_HEALTH_OVERHEAT) {

                textview.setText(currentBatteryHealth + " = OverHeat");
                r1.setRating(2);
            }

            if (deviceHealth == BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE) {

                textview.setText(currentBatteryHealth + " = Over voltage");
                r1.setRating(1);
            }

            if (deviceHealth == BatteryManager.BATTERY_HEALTH_UNKNOWN) {

                textview.setText(currentBatteryHealth + " = Below Average");
                r1.setRating(1);
            }
            if (deviceHealth == BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE) {

                textview.setText(currentBatteryHealth + " = Poor");
                r1.setRating(1);
            }


        }

    };
    }

