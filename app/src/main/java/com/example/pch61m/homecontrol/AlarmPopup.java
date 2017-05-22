package com.example.pch61m.homecontrol;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class AlarmPopup extends Activity {
    Button aceptar;
    Button cancelar;
    CheckBox chkv1;
    CheckBox chkv2;
    CheckBox chkp1;
    CheckBox chkp2;
    CheckBox chkpi;

    public static String EXTRA_P1 = "P1";
    public static String EXTRA_P2 = "P2";
    public static String EXTRA_V1 = "V1";
    public static String EXTRA_V2 = "V2";
    public static String EXTRA_PI = "PI";
    public boolean p1;
    public boolean p2;
    public boolean v1;
    public boolean v2;
    public boolean pi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_popup);

        aceptar = (Button) findViewById(R.id.btn_confirmation) ;
        cancelar = (Button) findViewById(R.id.btn_cancel_confirmation) ;
        chkv1 = (CheckBox)  findViewById(R.id.chkv1) ;
        chkv2 = (CheckBox)  findViewById(R.id.chkv2) ;
        chkp1 = (CheckBox)  findViewById(R.id.chkp1) ;
        chkp2 = (CheckBox)  findViewById(R.id.chkp2) ;
        chkpi = (CheckBox)  findViewById(R.id.chkpi) ;

        Intent i = getIntent();
        if(i.getIntExtra(EXTRA_P1,0) == 0){p1 = false;} else {p1 = true;}
        if(i.getIntExtra(EXTRA_P2,0) == 0){p2 = false;} else {p2 = true;}
        if(i.getIntExtra(EXTRA_V1,0) == 0){v1 = false;} else {v1 = true;}
        if(i.getIntExtra(EXTRA_V2,0) == 0){v2 = false;} else {v2 = true;}
        if(i.getIntExtra(EXTRA_PI,0) == 0){pi = false;} else {pi = true;}

        chkp1.setChecked(p1);
        chkp2.setChecked(p2);
        chkv1.setChecked(v1);
        chkv2.setChecked(v2);
        chkpi.setChecked(pi);

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_back = new Intent();
                int p1 = (Boolean.valueOf(chkp1.isChecked())) ? 1 : 0;
                int p2 = (Boolean.valueOf(chkp2.isChecked())) ? 1 : 0;
                int v1 = (Boolean.valueOf(chkv1.isChecked())) ? 1 : 0;
                int v2 = (Boolean.valueOf(chkv2.isChecked())) ? 1 : 0;
                int pi = (Boolean.valueOf(chkpi.isChecked())) ? 1 : 0;
                intent_back.putExtra("p1",String.valueOf(p1));
                intent_back.putExtra("p2",String.valueOf(p2));
                intent_back.putExtra("v1",String.valueOf(v1));
                intent_back.putExtra("v2",String.valueOf(v2));
                intent_back.putExtra("pi",String.valueOf(pi));
                setResult(RESULT_OK, intent_back);
                finish();
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
