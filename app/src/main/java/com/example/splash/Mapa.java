package com.example.splash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.splash.Json.MyInfo;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Mapa extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener {
    GoogleMap mMap;
    Button regresarmap;
    TextView lat,lon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapa);
        Intent intent = getIntent();
        Object object = null;
        MyInfo info = null;
        lat = findViewById(R.id.Lat);
        lon = findViewById(R.id.Long);
        object = intent.getExtras().get("MyInfo");
        info = (MyInfo) object;
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        regresarmap = findViewById(R.id.regresomapa);
        regresarmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Object object = null;
                MyInfo info = null;
                int i = 0;
                object = intent.getExtras().get("MyInfo");
                info = (MyInfo) object;
                Intent intent2 = new Intent(Mapa.this, entrada.class);
                intent2.putExtra("MyInfo", info);
                startActivity(intent2);
            }
        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {


        mMap = googleMap;
        this.mMap.setOnMapClickListener(this);
        this.mMap.setOnMapLongClickListener(this);
        String latitud = getIntent().getStringExtra("Latitud");
        String longitud = getIntent().getStringExtra("Longitud");
        lat = findViewById(R.id.Lat);
        lon = findViewById(R.id.Long);
        lat.setText("Latitud: "+latitud);
        lon.setText("Longitud"+longitud);
        LatLng mexico = new LatLng(Double.parseDouble(latitud),Double.parseDouble(longitud));
        mMap.addMarker(new MarkerOptions().position(mexico).title("Ubicacion"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(mexico));
    }


    @Override
    public void onMapClick(@NonNull LatLng latLng) {

    }

    @Override
    public void onMapLongClick(@NonNull LatLng latLng) {

    }

}
