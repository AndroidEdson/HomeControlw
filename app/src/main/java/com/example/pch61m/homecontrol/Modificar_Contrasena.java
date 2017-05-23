package com.example.pch61m.homecontrol;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pch61m.homecontrol.home.db.Inventory;
import com.example.pch61m.homecontrol.home.db.Users;

/**
 * Created by PCH61M on 21/05/2017.
 */

public class Modificar_Contrasena extends Activity {


    String id;
    Inventory inventory;
    private Button eliminar;
    private Button cancelar;
    private TextView txt_pass_access;
    private TextView txt_nip_acces;
    public static String KEY_ID="KEY_ID_MOD";
    private Users user;
    private int Acceso=0;
    private int NIP=0;
    int id_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_modificar_pass);


        Intent i = getIntent();

         id_user= i.getIntExtra(KEY_ID,0);

        inventory= new Inventory(getApplicationContext());
        user=inventory.getUserFromID(id_user);
        NIP= inventory.getNIP();


        eliminar = (Button) findViewById(R.id.btn_delete_confirmation) ;
        cancelar = (Button) findViewById(R.id.btn_cancel_confirmation) ;
        txt_pass_access = (TextView) findViewById(R.id.txt_PASS_ACCES);
        txt_nip_acces = (TextView) findViewById(R.id.txt_NIP_ACCES);
        Toast.makeText(getApplicationContext(), String.valueOf(NIP)+ " : " + user.getPass().toString() , Toast.LENGTH_SHORT).show();



        // Toast.makeText(getApplicationContext(), String.valueOf(NIP), Toast.LENGTH_SHORT).show();

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(txt_nip_acces.getText().toString().equals("") || txt_pass_access.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Campo vacío", Toast.LENGTH_SHORT).show();
                }else{

                    if(txt_nip_acces.getText().toString().equals( String.valueOf(NIP)) && txt_pass_access.getText().toString().equals(user.getPass() )){

                        Intent intent_back = new Intent();
                        setResult(RESULT_OK, intent_back);
                        finish();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Contraseña Incorrecta", Toast.LENGTH_SHORT).show();

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
