package com.example.pch61m.homecontrol;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pch61m.homecontrol.home.db.Inventory;
import com.example.pch61m.homecontrol.home.db.InventoryDbSchema;

/**
 * Created by PCH61M on 25/05/2017.
 */

public class new_profile extends Activity {



    String id;
    Inventory inventory;
    private Button eliminar;
    private Button cancelar;
    private TextView txt_pass;
    private int NIP=0;
    public static String KEY_ID="KEY_ID";
    private int id_user;
    private int last_ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_new_perfil);

        Intent i = getIntent();

        id_user= i.getIntExtra(KEY_ID,0);
        inventory= new Inventory(getApplicationContext());
        last_ID=inventory.getLastId(InventoryDbSchema.Profile_Table.NAME)+1;
        eliminar = (Button) findViewById(R.id.btn_delete_confirmation5) ;
        cancelar = (Button) findViewById(R.id.btn_cancel_confirmation5) ;
        txt_pass = (TextView) findViewById(R.id.txt_pass);

        // Toast.makeText(getApplicationContext(), String.valueOf(NIP), Toast.LENGTH_SHORT).show();

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (txt_pass.getText().toString().length()==0){

                    Toast.makeText(getApplicationContext(), "Campo Vacio", Toast.LENGTH_SHORT).show();

                }else {

                        inventory.AddProfile(last_ID, txt_pass.getText().toString());
                        inventory.AddUserProfile(id_user, last_ID);
                        Intent intent_back = new Intent();
                        setResult(RESULT_OK, intent_back);
                        finish();

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
