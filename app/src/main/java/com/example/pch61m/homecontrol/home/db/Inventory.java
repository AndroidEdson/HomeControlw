package com.example.pch61m.homecontrol.home.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PCH61M on 12/05/2017.
 */
//********************************************************
//********************************************************
//********************************************************

//__________________________________________________________________________________________________________________
//__________________________________________________________________________________________________________________
                                        // USER CURSOR
class UsersCursor extends CursorWrapper {

    public UsersCursor(Cursor cursor) {super(cursor);}


    public Users getUser(){
        Cursor cursor = getWrappedCursor();
        return  new Users (cursor.getInt(cursor.getColumnIndex(InventoryDbSchema.Users_Table.Columns.ID)),
                cursor.getString(cursor.getColumnIndex(InventoryDbSchema.Users_Table.Columns.NAME)),
                cursor.getString(cursor.getColumnIndex(InventoryDbSchema.Users_Table.Columns.LASTNAME)),
                cursor.getString(cursor.getColumnIndex(InventoryDbSchema.Users_Table.Columns.EMAIL)),
                cursor.getString(cursor.getColumnIndex(InventoryDbSchema.Users_Table.Columns.PASSWORD)));
    }

}

//__________________________________________________________________________________________________________________
//__________________________________________________________________________________________________________________
// PROFILE CURSOR
class ProfileCursor extends CursorWrapper {

    public ProfileCursor(Cursor cursor) {super(cursor);}


  public Profiles getProfile(){
      Cursor cursor = getWrappedCursor();
      return  new Profiles (cursor.getInt(cursor.getColumnIndex(InventoryDbSchema.Profile_Table.Columns.ID)),
              cursor.getString(cursor.getColumnIndex(InventoryDbSchema.Profile_Table.Columns.DESCRIPTION)),
              cursor.getString(cursor.getColumnIndex(InventoryDbSchema.Profile_Table.Columns.ACTUATORS)));
  }

}



//__________________________________________________________________________________________________________________
//__________________________________________________________________________________________________________________
// ProfileUser CURSOR
class UserProfileCursor extends CursorWrapper {

    public UserProfileCursor(Cursor cursor) {super(cursor);}


    public UsersProfile getUserProfile(){
        Cursor cursor = getWrappedCursor();
        return  new UsersProfile (cursor.getInt(cursor.getColumnIndex(InventoryDbSchema.UserProfile_Table.Columns.ID_USER)),
                cursor.getInt(cursor.getColumnIndex(InventoryDbSchema.UserProfile_Table.Columns.ID_PROFILE)) );
    }

}


//********************************************************
//********************************************************
//********************************************************

//INICIO DE FUNCIONES INVENTORY
public final class Inventory {

    private InventoryHelper inventoryHelper;
    private SQLiteDatabase db;

    public  Inventory(Context context) {
        inventoryHelper = new InventoryHelper(context);
        db = inventoryHelper.getWritableDatabase();
    }


//********************************************************
//********************************************************
//********************************************************

//__________________ FUNCIONES_________________________________________



// PARA OBTENER TODOS LOS USUARIOS
    public List<Users> getAllUsers() {
        List<Users> list = new ArrayList<Users>();

        UsersCursor cursor = new UsersCursor((db.rawQuery("SELECT * FROM users ORDER BY id", null)));

        while (cursor.moveToNext()) {

            list.add((cursor.getUser()));  // metodo wrappcursor
        }
        cursor.close();
        return list;

    }

    //OBTENER ID USER

    public Users getUserID(String email, String pass ) {
        Users list ;


        UsersCursor cursor = new UsersCursor((db.rawQuery( "SELECT * FROM users WHERE  ( UPPER(name) LIKE '%"+email+"%' OR email LIKE '%"+email+"%') and pass LIKE '%"+pass+"%'" , null)));


        cursor.moveToNext();
            list=cursor.getUser();  // metodo wrappcursor


        cursor.close();
        return list;
    }


    //OBTENER USER FROM ID

    public Users getUserFromID(int id) {
        Users list ;


        UsersCursor cursor = new UsersCursor((db.rawQuery("SELECT * FROM users WHERE id="+String.valueOf(id) , null)));
        cursor.moveToNext();
        list=cursor.getUser();  // metodo wrappcursor


        cursor.close();
        return list;
    }


    public  int getNIP() {

        //  Cursor cursor = db.rawQuery("SELECT * FROM categories ORDER BY id", null);
        int NIP=0;
        String query = "SELECT * FROM pass_door" ;

        Cursor cursor = (db.rawQuery(query, null));

        if (cursor.getCount() >= 1) {
            cursor.moveToFirst();
            NIP= cursor.getInt(cursor.getColumnIndex("NIP"));

        }
        else{
            NIP=0;
        }
        //List<Products> list = new ArrayList<Products>();
        return NIP;

    }

    public  void  updateUser(String id, String new_name,String new_last_name,String new_email,String new_password )
    {


        ContentValues values = new ContentValues();
        values.put(InventoryDbSchema.Users_Table.Columns.NAME, new_name);// asegura que siempre da correcto
        values.put(InventoryDbSchema.Users_Table.Columns.LASTNAME, new_last_name);
        values.put(InventoryDbSchema.Users_Table.Columns.EMAIL, new_email);
        values.put(InventoryDbSchema.Users_Table.Columns.PASSWORD, new_password);

        db.update(InventoryDbSchema.Users_Table.NAME, values, InventoryDbSchema.Users_Table.Columns.ID + " = ?", new String[]{id});

    }

    public  void  updateNIP(String id, int new_password ) {

        ContentValues values = new ContentValues();

        values.put(InventoryDbSchema.UserNIP.Columns.NIP, new_password);

        db.update(InventoryDbSchema.UserNIP.NAME, values, InventoryDbSchema.UserNIP.Columns.ID + " = ?", new String[]{id});


    }


    public int getLastId(String TableName)
    {

        //
        Cursor cursor= (db.rawQuery("SELECT  MAX(id) FROM "+TableName, null));

        cursor.moveToFirst();

        //List<Products> list = new ArrayList<Products>();
        int maxid= cursor.getInt(cursor.getColumnIndex("MAX(id)"));
        return maxid;

    }


    public void addUser(int id, String name, String lastName, String Email, String pass  )
    {
        ///Agregar un elemento a la base de datos

        db =inventoryHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(InventoryDbSchema.Users_Table.Columns.ID, id);// asegura que siempre da correcto
        contentValues.put(InventoryDbSchema.Users_Table.Columns.NAME, name);// asegura que siempre da correcto
        contentValues.put(InventoryDbSchema.Users_Table.Columns.LASTNAME, lastName);
        contentValues.put(InventoryDbSchema.Users_Table.Columns.EMAIL, Email);
        contentValues.put(InventoryDbSchema.Users_Table.Columns.PASSWORD, pass);


        db.insert(InventoryDbSchema.Users_Table.NAME, null, contentValues);

        // Cursor cursor = new CategoryCursor((db.insert("categories", null , contentValues )));
    }



} //END INVENTORY
