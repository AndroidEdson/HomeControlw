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
import com.example.pch61m.homecontrol.home.db.Users;

/**
 * Created by PCH61M on 23/05/2017.
 */

public class Add_User extends Activity {



    String id;
    Inventory inventory;
    private Button eliminar;
    private Button cancelar;
    private TextView txt_Add_name;
    private TextView txt_Add_lastname;
    private TextView txt_Add_email;
    private TextView txt_Add_password;
    int lastID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_add_user);

        inventory= new Inventory(getApplicationContext());


        eliminar = (Button) findViewById(R.id.btn_delete_confirmation3) ;
        cancelar = (Button) findViewById(R.id.btn_cancel_confirmation3) ;
        txt_Add_name = (TextView) findViewById(R.id.txt_add_name);
        txt_Add_lastname = (TextView) findViewById(R.id.txt_add_lastname);
        txt_Add_email = (TextView) findViewById(R.id.txt_add_email);
        txt_Add_password = (TextView) findViewById(R.id.txt_add_password);


        lastID= inventory.getLastId(InventoryDbSchema.Users_Table.NAME)+1;

        Toast.makeText(getApplicationContext(), String.valueOf(lastID), Toast.LENGTH_SHORT).show();


        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(txt_Add_name.getText().toString().equals("") || txt_Add_lastname.getText().toString().equals("")|| txt_Add_email.getText().toString().equals("") || txt_Add_password.getText().toString().equals("") )
                {
                    Toast.makeText(getApplicationContext(), "Campo vacío", Toast.LENGTH_SHORT).show();
                }else{

                    if(txt_Add_password.getText().length()<=4){

                        Toast.makeText(getApplicationContext(), "La contraseña debe contener mínimo 5 Carácteres", Toast.LENGTH_SHORT).show();

                    }
                    else if (!txt_Add_email.getText().toString().contains("@")){
                        Toast.makeText(getApplicationContext(), "Correo Inválido", Toast.LENGTH_SHORT).show();

                    }
                    else{

                        String new_name = txt_Add_name.getText().toString();
                        String new_lastname = txt_Add_lastname.getText().toString();
                        String new_email = txt_Add_email.getText().toString();
                        String new_password = txt_Add_password.getText().toString();
                        inventory.addUser(lastID, new_name, new_lastname, new_email, new_password);
                        Toast.makeText(getApplicationContext(), "Usuario Añadido!", Toast.LENGTH_SHORT).show();
                        finish();


                    }


                }




            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Cancelado", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


    }






}
