package com.example.battery;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.widget.Toast;


import static com.example.battery.R.raw.alarmy;


public class Alarmb extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context,"Desired level reached",Toast.LENGTH_LONG).show();
        Vibrator v=(Vibrator) context.getSystemService(context.VIBRATOR_SERVICE);

            v.vibrate(3000);
            final MediaPlayer player;
            player = MediaPlayer.create(context.getApplicationContext(), alarmy);
            // player.start();
            CountDownTimer cntr_aCounter = new CountDownTimer(5000, 2000) {
                public void onTick(long millisUntilFinished) {

                    player.start();
                }

                public void onFinish() {
                    //code fire after finish
                    player.stop();
                    player.stop();
                }
            };
            cntr_aCounter.start();

        }
    }

