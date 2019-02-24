package com.example.aprilia.april_1202160098_si4002_pab_modul3;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class splashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        CountDownTimer mundur = new CountDownTimer(3000, 1000) { //hitung mundur (timer splash screen)
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                startActivity(new Intent(splashScreen.this, MainActivity.class));
            }
        };
        mundur.start();
    }
}
