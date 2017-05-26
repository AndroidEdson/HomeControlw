package com.example.pch61m.homecontrol;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class BeginActivity extends AppCompatActivity {
    private static  int SPLASH_TIME_OUT= 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin);

        //prueba eroza 2
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent= new Intent(BeginActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME_OUT);


    }





}
