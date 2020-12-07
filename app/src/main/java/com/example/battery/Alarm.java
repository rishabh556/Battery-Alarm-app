package com.example.battery;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class Alarm extends AppCompatActivity {
    ImageView iv_battery;
    TextView tv_battery;
    Handler handler;
    Runnable runnable;
ToggleButton tb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        iv_battery=findViewById(R.id.iv_battery);
        tv_battery=findViewById(R.id.tv_battery);
        tb = findViewById(R.id.toggleButton);
        runnable= new Runnable() {
            @Override
            public void run() {
                int level = (int) batteryLevel();
                tv_battery.setText("BATTERY: "+level+"%");

//set battery percentage here
                if(level==100 && tb.isChecked()){
                    Toast.makeText(getApplicationContext(),"Alarm Activated",Toast.LENGTH_LONG).show();
                    int time=3;
                    Intent i = new Intent(Alarm.this, Alarmb.class);
                    PendingIntent pi=PendingIntent.getBroadcast(getApplicationContext(),0,i,0);
                    AlarmManager am =(AlarmManager) getSystemService(ALARM_SERVICE);
                    am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+time*1000,pi);

                }
//                if(!tb.isChecked()){
//                    Toast.makeText(getApplicationContext(),"Alarm Deactivated",Toast.LENGTH_LONG).show();
//                }
                if(level>75){
                    iv_battery.setImageResource(R.drawable.battery_75);
                }
                if(level>50 && level<=75){
                    iv_battery.setImageResource(R.drawable.battery_75);
                }
                if(level>25 && level<=50){
                    iv_battery.setImageResource(R.drawable.battery_50);
                }
                if(level>5 && level<=25){
                    iv_battery.setImageResource(R.drawable.battery_25);
                }
                if(level<=5 ){
                    iv_battery.setImageResource(R.drawable.battery_00);
                }

//                if(level>50){
//
//                    BluetoothAdapter.getDefaultAdapter().enable();
//
//
//                }
                handler.postDelayed(runnable, 5000);

            }
        };
        handler = new Handler();
        handler.postDelayed(runnable, 0);

    }



    public float batteryLevel(){
        Intent batteryIntent = registerReceiver (null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int level = batteryIntent . getIntExtra (BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryIntent . getIntExtra (BatteryManager.EXTRA_SCALE, -1);
        if (level == -1 || scale == -1) {
            return 50.0f;
        }
        return ((float) level /(float) scale) * 100.0f;

    }
}