package com.example.splash.contract;
import android.content.ContentValues;
import android.provider.BaseColumns;
import static com.example.splash.BD.UsuariosDBService.TABLE_PASS;
import static com.example.splash.BD.UsuariosDBService.TABLE_USER;

import com.example.splash.Json.MyData;
import com.example.splash.Json.MyInfo;

import java.io.Serializable;

public class UsuariosContract implements Serializable {
    public static final String TAG = "UsuariosContract";

    public static abstract class UsuarioEntry implements BaseColumns{
        public static final String USUARIO = "usuario";


        public static final String getCreateTable( ){
            String table = "CREATE TABLE " + TABLE_USER + "(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "usuario TEXT NOT NULL UNIQUE," +
                    "psswd TEXT NOT NULL," +
                    "email TEXT NOT NULL," +
                    ")";
            return table;
        }
        public static ContentValues toContentValues(MyInfo info){
            ContentValues values = new ContentValues();
            values.put("usuario", info.getUsuario());
            values.put("psswd", info.getContraseña());
            values.put("email", info.getCorreo());
            return values;
        }
    }

    public abstract static class MyDataEntry implements BaseColumns{
        public static final String getCreateTable( )
        {
            String table ="CREATE TABLE "+TABLE_PASS+"(" +
                    "id_pass INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "pass TEXT NOT NULL," +
                    "user_p TEXT NOT NULL," +
                    "id INTEGER NOT NULL)";
            return table;
        }
        public static ContentValues toContentValues(MyData myData)
        {
            ContentValues values = new ContentValues();
            values.put("pass", myData.getContra());
            values.put("user_p", myData.getPassRed());

            return values;
        }
    }
}
