package com.example.splash;

import androidx.appcompat.app.AppCompatActivity;

import com.example.splash.Json.MyInfo;
import com.google.gson.Gson;

public class Json_datos extends AppCompatActivity{

    public static String crearJson(String usuario , String contraseña , String correo)
    {

        MyInfo datos = new MyInfo();
        Gson gson = new Gson();

        datos.setUsuario(usuario);
        datos.setContraseña(contraseña);
        datos.setCorreo(correo);


        String nuevojson = gson.toJson(datos);

        return nuevojson;
    }

    public static MyInfo leerJson(String textoJson){
        Gson gson = new Gson();
        MyInfo datos = gson.fromJson(textoJson, MyInfo.class);

        return datos;
    }

    public static String crearJsonPerfil(String usuario , String contraseña , int Image)
    {

        Perfil datos = new Perfil();
        Gson gson = new Gson();

        datos.setNamePerfil(usuario);
        datos.setPassPerfil(contraseña);
        datos.setImage(Image);

        String nuevojson = gson.toJson(datos);

        return nuevojson;
    }

    public static Perfil leerJsonPerfil(String textoJson){
        Gson gson = new Gson();
        Perfil datos = gson.fromJson(textoJson, Perfil.class);

        return datos;
    }
}

