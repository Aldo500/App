package com.example.splash.BD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.splash.contract.UsuariosContract;
import com.example.splash.Json.MyInfo;

import java.util.ArrayList;
import java.util.List;

public class DBUsuarios extends UsuariosDBService{
    Context context;

    public DBUsuarios(@Nullable Context context){
        super(context);
        this.context = context;
    }

    public long saveUsr(MyInfo info){
        long id = 0;
        try{
            UsuariosDBService usuariosDBService = new UsuariosDBService(context);
            SQLiteDatabase db = usuariosDBService.getWritableDatabase();

            ContentValues values = new ContentValues();
            id = db.insert(TABLE_USER,null, UsuariosContract.UsuarioEntry.toContentValues(info));

        }catch(Exception ex){
            ex.toString();
        }
        finally{
            return id;
        }
    }

    public List<MyInfo> getUsuarios( )
    {
        SQLiteDatabase sqLiteDatabase = null;
        Cursor cursor = null;
        List<MyInfo>usuarios = null;
        MyInfo usuario = null;

        sqLiteDatabase = getReadableDatabase();
        cursor = sqLiteDatabase.rawQuery("SELECT*FROM "+TABLE_USER,null);
        if( cursor == null )
        {
            return null;
        }
        if( cursor.getCount() < 1)
        {
            return null;
        }
        if( !cursor.moveToFirst() )
        {
            return null;
        }
        Log.d(TAG, "" + cursor.getCount());
        usuarios = new ArrayList<MyInfo>( );
        for( int i = 0; i < cursor.getCount(); i++)
        {
            usuario = new MyInfo( );
            usuario.setUsuario(cursor.getString( 0 ));
            usuario.setContraseña(cursor.getString(1));
            usuario.setCorreo(cursor.getString(2));
            usuarios.add(usuario);
            cursor.moveToNext( );
        }
        return usuarios;
    }

    public MyInfo GetUsuario(String usr){
        MyInfo info = new MyInfo();
        UsuariosDBService usuariosDBService = new UsuariosDBService(context);
        SQLiteDatabase db = usuariosDBService.getReadableDatabase();
        Cursor cursor=null;
        String query = "SELECT * FROM table_usuarios WHERE usuario = ?";
        String[] args = {usr};

        cursor = db.rawQuery(query,args);
        if (cursor.moveToFirst()) {
            info.setUsuario( cursor.getString( 1 ) );
            info.setContraseña(cursor.getString(2));
            info.setCorreo(cursor.getString(3));
            return info;
        }

        cursor.close();
        return null;
    }

    public MyInfo GetUsuario(String usr,String mail){
        MyInfo info = new MyInfo();
        UsuariosDBService usuariosDBService = new UsuariosDBService(context);
        SQLiteDatabase db = usuariosDBService.getReadableDatabase();
        Cursor cursor = null;
        String query = "SELECT * FROM table_usuarios WHERE usuario = ? AND mail = ?";
        String[] args = {usr,mail};

        cursor = db.rawQuery(query,args);
        if (cursor.moveToFirst()) {
            info.setUsuario( cursor.getString( 1 ) );
            info.setContraseña(cursor.getString(2));
            info.setCorreo(cursor.getString(3));
            return info;
        }

        cursor.close();
        return null;
    }

    public boolean AlterUser(String usr,String pass){
        boolean ok = false;
        UsuariosDBService usuariosDBService = new UsuariosDBService(context);
        SQLiteDatabase db =usuariosDBService.getWritableDatabase();
        try{
            db.execSQL("UPDATE " + TABLE_USER + " SET psswd = '" + pass + "' WHERE usuario='" + usr + "'");
            ok = true;
        }catch(Exception ex){
            ex.toString();
        }
        return ok;
    }
}
