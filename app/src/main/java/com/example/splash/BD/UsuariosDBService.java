package com.example.splash.BD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.splash.contract.UsuariosContract;


public class UsuariosDBService extends SQLiteOpenHelper {
    public static final String TAG = "UsuariosDBService";
    public static final int DATABASE_VERSION = 5;
    public static final String DATABASE_NAME = "USUARIOS.db";

    public static final String TABLE_USER= "table_usuarios";
    public static final String TABLE_PASS = "table_pass";

    public UsuariosDBService(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(UsuariosContract.UsuarioEntry.getCreateTable());
        sqLiteDatabase.execSQL(UsuariosContract.MyDataEntry.getCreateTable());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_USER);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_PASS);
        onCreate(sqLiteDatabase);
    }

}
