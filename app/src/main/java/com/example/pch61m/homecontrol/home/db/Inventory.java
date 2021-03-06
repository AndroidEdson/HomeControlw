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

class UserToProfileCursor extends CursorWrapper {

    public UserToProfileCursor(Cursor cursor) {super(cursor);}

    public User_to_profile getUserToProfile(){
        Cursor cursor = getWrappedCursor();
        return  new User_to_profile (cursor.getInt(cursor.getColumnIndex(InventoryDbSchema.User_to_profile_Table.Columns.ID)),
                cursor.getInt(cursor.getColumnIndex(InventoryDbSchema.User_to_profile_Table.Columns.ID_PROFILE)),
                cursor.getString(cursor.getColumnIndex(InventoryDbSchema.User_to_profile_Table.Columns.DESCRIPTION)),
                cursor.getString(cursor.getColumnIndex(InventoryDbSchema.User_to_profile_Table.Columns.ACTUATORS)));
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




    public  List<User_to_profile> getProfiles(String id_user) {

        List<User_to_profile> list = new ArrayList<User_to_profile>();

        //  Cursor cursor = db.rawQuery("SELECT * FROM categories ORDER BY id", null);

        String query_macizo =" SELECT a.id as id , c.id as id_profile, description, actuators \n" +
                " FROM  users a\n" +
                "INNER JOIN users_profile b ON ( a.id = b.id_user) \n" +
                "INNER JOIN profiles  c ON ( c.id = b.id_profile)\n" +
                "WHERE a.id="+id_user+ " ORDER BY c.description ASC";

        UserToProfileCursor cursor = new UserToProfileCursor(db.rawQuery(query_macizo, null));

        while (cursor.moveToNext()) {
            list.add((cursor.getUserToProfile()));
        }

        return list;
    }

    public void AddProfile(int id_user, String description )
    {
        ///Agregar un elemento a la base de datos
        db =inventoryHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(InventoryDbSchema.Profile_Table.Columns.ID, id_user);
        contentValues.put(InventoryDbSchema.Profile_Table.Columns.DESCRIPTION, description);
        contentValues.put(InventoryDbSchema.Profile_Table.Columns.ACTUATORS, "JML1250L2250B101B201R1FFFFFFR2FFFFFF");
        db.insert(InventoryDbSchema.Profile_Table.NAME, null, contentValues);
        // Cursor cursor = new CategoryCursor((db.insert("categories", null , contentValues )));
    }


    public void AddUserProfile(int id_user, int id_profile )
    {
        ///Agregar un elemento a la base de datos
        db =inventoryHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(InventoryDbSchema.UserProfile_Table.Columns.ID_USER, id_user);
        contentValues.put(InventoryDbSchema.UserProfile_Table.Columns.ID_PROFILE, id_profile);
        db.insert(InventoryDbSchema.UserProfile_Table.NAME, null, contentValues);
        // Cursor cursor = new CategoryCursor((db.insert("categories", null , contentValues )));
    }


} //END INVENTORY
