package com.example.splash.Permisos;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import java.io.Serializable;

public class permisos implements Serializable {

    public boolean conPermisoCamara = false, conPermisoInternet= false;
    public static final int COD_PERCAM = 0, COD_PERMINT=1;

    public void PermisosDeInternet(Context context, Activity activity) {
        int estadoPer = ContextCompat.checkSelfPermission(context, Manifest.permission.INTERNET);
        if (estadoPer == PackageManager.PERMISSION_GRANTED) {
            permisoInternetAcept(context);
        } else {
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.INTERNET},
                    COD_PERMINT);
        }
    }
    public void PermisosDeCamara(Context context, Activity activity) {
        int estadoDePermiso = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA);
        if (estadoDePermiso == PackageManager.PERMISSION_GRANTED) {
            permisoCamAcept(context);
        } else {

            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.CAMERA},
                    COD_PERCAM);
        }
    }
    public void permisoCamAcept(Context context) {
        Toast.makeText(context, "Se tienen los permisos de a cámara", Toast.LENGTH_SHORT).show();
        conPermisoCamara = true;
    }

    public void permisoCamNoAcept(Context context) {
        Toast.makeText(context, "No se tienen los permisos de la cámara", Toast.LENGTH_SHORT).show();
    }
    public void permisoInternetAcept(Context context) {
        Toast.makeText(context, "Se tienen los permisos de internet", Toast.LENGTH_SHORT).show();
        conPermisoInternet = true;
    }

    public void permisoInternetNoAcept(Context context) {
        Toast.makeText(context, "No se tienen los permisos de internet", Toast.LENGTH_SHORT).show();
    }
}
