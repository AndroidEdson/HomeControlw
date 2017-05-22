package com.example.pch61m.homecontrol;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pch61m.homecontrol.home.db.Inventory;

/**
 * Created by PCH61M on 21/05/2017.
 */

public class LoginDoor extends  Activity {



        String id;
         Inventory inventory;
         private Button eliminar;
        private Button cancelar;
         private TextView txt_pass;
        private int NIP=0;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.login_door);

            inventory= new Inventory(getApplicationContext());
            NIP= inventory.getNIP();

            eliminar = (Button) findViewById(R.id.btn_delete_confirmation) ;
            cancelar = (Button) findViewById(R.id.btn_cancel_confirmation) ;
            txt_pass = (TextView) findViewById(R.id.txt_pass);
           // Toast.makeText(getApplicationContext(), String.valueOf(NIP), Toast.LENGTH_SHORT).show();

            eliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (txt_pass.getText().toString().length()==0){

                        Toast.makeText(getApplicationContext(), "NIP Vacio", Toast.LENGTH_SHORT).show();

                    }else {


                        if (NIP == Integer.valueOf(txt_pass.getText().toString())) {
                            Intent intent_back = new Intent();
                            setResult(RESULT_OK, intent_back);
                            finish();

                        } else {
                            Toast.makeText(getApplicationContext(), "NIP Incorrecto", Toast.LENGTH_SHORT).show();

                        }
                    }

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
