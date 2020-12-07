package com.example.battery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.TextView;

public class OReport extends AppCompatActivity {
    TextView txtBatteryStatus, txtBatteryPlug,txtBatteryHealth,txtBatteryVoltage,txtBatteryTemp,txtBatterytech,txtBatterylevel;
    IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oreport);

        txtBatteryStatus=findViewById(R.id.txtstatus);
        txtBatteryPlug=findViewById(R.id.txtplug);
        txtBatteryHealth=findViewById(R.id.txtHealth);
        txtBatteryVoltage=findViewById(R.id.txtVoltage);
        txtBatteryTemp=findViewById(R.id.txtTemp);
        txtBatterytech=findViewById(R.id.txtTech);
        txtBatterylevel=findViewById(R.id.txtLevel);


        intentFilter=new IntentFilter(Intent.ACTION_BATTERY_CHANGED);

        final BroadcastReceiver broadcastReceiver=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
                boolean isCharging = status ==BatteryManager.BATTERY_STATUS_CHARGING ||status==BatteryManager.BATTERY_STATUS_FULL;

                if(isCharging)
                    txtBatteryStatus.setText("Battery Status : Charging");
                else
                    txtBatteryStatus.setText("Battery Status : Not Charging ");

                int chargePlug= intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
                boolean isUSBcharge= chargePlug==BatteryManager.BATTERY_PLUGGED_USB || chargePlug==BatteryManager.BATTERY_PLUGGED_AC;
                if(isUSBcharge)
                    txtBatteryPlug.setText("Power Source : USB");
                else
                    txtBatteryPlug.setText("Power Source : AC ");


                int level= intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
                int Scale= intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
                float batteryPct =(level/(float)Scale)*100;
                txtBatterylevel.setText("Batterylevel : "+ batteryPct+"%");

                int Voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, -1);
                txtBatteryVoltage.setText("Battery Voltage :"+Voltage+"mV");


                float temp = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, -1);
                float temp1=temp/10;
                txtBatteryTemp.setText("Device Temperature : "+temp1+" °C");


                String tech = intent.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY);
                txtBatterytech.setText("Battery Technology:"+tech);


                int health = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, -1);

                switch(health)
                {
                    case BatteryManager.BATTERY_HEALTH_COLD:
                        txtBatteryHealth.setText("Battery Health : Cold");
                        break;

                    case BatteryManager.BATTERY_HEALTH_DEAD:
                        txtBatteryHealth.setText("Battery Health : Dead");
                        break;

                    case BatteryManager.BATTERY_HEALTH_GOOD:
                        txtBatteryHealth.setText("Battery Health : Good");
                        break;

                    case BatteryManager.BATTERY_HEALTH_OVERHEAT:
                        txtBatteryHealth.setText("Battery Health : Overheat");
                        break;

                    case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
                        txtBatteryHealth.setText("Battery Health : Voltage");
                        break;


                    case BatteryManager.BATTERY_HEALTH_UNKNOWN:
                        txtBatteryHealth.setText("Battery Health : Unknown");
                        break;
                    case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
                        txtBatteryHealth.setText("Battery Health : Failure");
                        break;
                    default:
                        break;

                }


            }
        };


        OReport.this.registerReceiver(broadcastReceiver,intentFilter);



    }
}
