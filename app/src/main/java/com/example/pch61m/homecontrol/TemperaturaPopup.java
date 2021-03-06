package com.example.pch61m.homecontrol;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class TemperaturaPopup extends Activity {
    Button aceptar;
    Button cancelar;
    EditText editText;
    public static String EXTRA_P1 = "P1";
    public static String EXTRA_P2 = "P2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temperatura_popup);
        aceptar = (Button) findViewById(R.id.btn_confirmation) ;
        cancelar = (Button) findViewById(R.id.btn_cancel_confirmation) ;
        editText = (EditText) findViewById(R.id.editText) ;

        Intent i = getIntent();
        int aux = i.getIntExtra(EXTRA_P1,30);
        editText.setText(String.valueOf(aux));
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_back = new Intent();
                intent_back.putExtra("t1",String.valueOf(editText.getText()));
                setResult(RESULT_OK, intent_back);
                finish();
                //
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
