package com.example.splash;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.splash.Json.MyInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.text.BreakIterator;

public class OlvidePass extends AppCompatActivity {
    Button button1;
    public static String usr,passw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_olvidepass);
        button1=findViewById(R.id.loginid3);
    }

    public void RecuperarContraseña (View v){
        EditText userName = (EditText) findViewById(R.id.editTextusrPass);
        EditText Mail = (EditText) findViewById(R.id.editTextpassPass);

        usr = String.valueOf(userName.getText());

        String mensaje="";


        if("".equals(userName.getText().toString()) || "".equals(Mail.getText().toString()))
        {
            mensaje = "Falta un dato";
        }else{
            boolean TipoCorreo = false;
            String Correo = "";
            for(int x = 0 ; x < Mail.length(); x++){
                if(Mail.getText().charAt(x) == '@'){
                    for(int y = x ; y < Mail.length(); y++){
                        Correo = Correo + Mail.getText().charAt(y);
                    }
                    if("@gmail.com".equals(Correo) || "@hotmail.com".equals(Correo) || "@outlook.com".equals(Correo)){
                        TipoCorreo = true;
                    }
                    break;
                }
            }
            if(userName.length() > 20 || Mail.length() > 40 || TipoCorreo == false){
                mensaje = "Parametro Erroneo";
                if(userName.length() > 20){mensaje = "Nombre de usuario muy largo";}
                if(Mail.length() > 70){mensaje = "Correo muy largo";}
                if(TipoCorreo == false){mensaje = "Correo invalido";}
            }else {
                try {
                    Json_datos json = new Json_datos();

                    boolean BucleArchivo = true;
                    int x = 1;
                    int numArchivo = 0;
                    while (BucleArchivo) {
                        File Cfile = new File(getApplicationContext().getFilesDir() + "/" + "Archivo" + x + ".txt");
                        if(Cfile.exists()) {
                            Des myDes = new Des();

                            BufferedReader file = new BufferedReader(new InputStreamReader(openFileInput("Archivo" + x + ".txt")));
                            String lineaTexto = file.readLine();
                            file.close();

                            MyInfo datos = json.leerJson(lineaTexto);
                            String valorName = datos.getUsuario();
                            String valorMail = datos.getCorreo();
                            String contra= datos.getContraseña();
                            String nueva = String.format("%d",(int)(Math.random()*10000));
                            mensaje="<html><head><title>Recuperar Contraseña</title></head><body><div><h1>RECUPERAR CONTRASEÑA :)</h1><br><br><p>Esta es tu nueva contraseña:"+contra+"</p><p>Tu anterior contraseña era:"+nueva+"</p></div></body></html>";

                            if (valorName.equals(userName.getText().toString()) & valorMail.equals(Mail.getText().toString())) {
                                mensaje = "Usuario encontrado";
                                BucleArchivo = false;
                            } else {
                                x = x + 1;
                            }
                        }else{
                            mensaje = "Usuario no encontrado";
                            BucleArchivo = false;
                        }
                    }

                    if("Usuario Encontrado".equals(mensaje)){
                        String text = "Para recuperar la contraseña haga clic aqui --->";
                        if( text == null || text.length() == 0 )
                        {
                            mensaje = "No hay correo";
                        }
                        if( sendInfo( text ) )
                        {
                            mensaje = "Correo enviado";
                        }
                        else
                        {
                            mensaje = "Error al enviar el correo";
                        }
                    }

                } catch (Exception e) {
                    mensaje = "Error dentro del archivo";
                }
            }
        }
        Toast.makeText(OlvidePass.this, mensaje, Toast.LENGTH_SHORT).show();
    }

    public void Volver (View v){
        Intent intent = new Intent (OlvidePass.this, login.class);
        startActivity( intent );
    }

    public boolean sendInfo( String text )
    {
        String TAG = "App";
        JsonObjectRequest jsonObjectRequest = null;
        JSONObject jsonObject = null;
        String url = "https://us-central1-nemidesarrollo.cloudfunctions.net/envio_correo";
        RequestQueue requestQueue = null;
        if( text == null || text.length() == 0 )
        {
            return false;
        }
        jsonObject = new JSONObject( );
        try
        {
            jsonObject.put("hola" , "mundo" );
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response)
            {
                Log.i(TAG, response.toString());
            }
        } , new  Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.toString());
            }
        } );
        requestQueue = Volley.newRequestQueue( getBaseContext() );
        requestQueue.add(jsonObjectRequest);

        return true;
    }
}
