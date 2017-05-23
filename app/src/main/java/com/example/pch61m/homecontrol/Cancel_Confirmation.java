package com.example.pch61m.homecontrol;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by PCH61M on 16/05/2017.
 */

public class Cancel_Confirmation extends Activity{

    String id;
    Button eliminar;
    Button cancelar;
    public static String KEY_OK ="com.example.pch61m.homecontrol.KEY_OK";
    public static String KEY_CANCEL ="com.example.pch61m.homecontrol.KEY_CANCEL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_confirmation);

        eliminar = (Button) findViewById(R.id.btn_delete_confirmation) ;
        cancelar = (Button) findViewById(R.id.btn_cancel_confirmation) ;

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_back = new Intent();
                intent_back.putExtra(KEY_OK , 1);
                setResult(RESULT_OK, intent_back);
                finish();
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_back = new Intent();
                intent_back.putExtra(KEY_OK , 0);
                setResult(RESULT_OK, intent_back);
                finish();
            }
        });


    }




}
