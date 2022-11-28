package com.example.splash;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.splash.Json.MyData;
import com.example.splash.Json.MyInfo;
import com.example.splash.MyAdapter.MyAdapter;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class entrada extends AppCompatActivity {
    private TextView usrtext;
    private ListView listView;
    private List<MyData> list;
    private int []cuentas = {R.drawable.classroom,R.drawable.gmail,R.drawable.facebook,R.drawable.zoom};
    EditText editPass;
    private List<MyData> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entrada);


        String aux = null;
        MyInfo info = null;
        MyData myData = null;
        Object object = null;
        editPass = findViewById(R.id.editPass);
        usrtext = findViewById(R.id.datosusr);
        listView = (ListView) findViewById(R.id.listViewId);
        lista = new ArrayList<MyData>();

        Intent intent = getIntent();
        for(int i = 0; i <4; i++){
            myData = new MyData();
            myData.setPassRed(String.format("Contraseña: %d" , (int)(Math.random()*1000000)));
            if(i == 0){
                myData.setRed(String.format("Classroom"));
                myData.setPerfil(cuentas[0]);
            }
            if(i == 1){
                myData.setRed(String.format("Gmail"));
                myData.setPerfil(cuentas[1]);
            }
            if(i == 2){
                myData.setRed(String.format("Facebook"));
                myData.setPerfil(cuentas[2]);
            }
            if(i == 3){
                myData.setRed(String.format("Zoom"));
                myData.setPerfil(cuentas[3]);
            }

            list.add(myData);
        }

        MyAdapter myAdapter = new MyAdapter(list, getBaseContext());
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                toast(i);
            }
        });

        if( intent != null){
            aux = intent.getStringExtra("Usuario");
            if(aux != null && aux.length()>0){
                usrtext.setText(aux);
            }
            if( intent.getExtras() != null){
                object = intent.getExtras().get("MyInfo");
                if(object != null){
                    if(object instanceof MyInfo){
                        info = (MyInfo) object;
                        usrtext.setText("Usuario " + info.getUsuario());
                    }
                }
            }
        }
    }
    private void toast(int i)
    {
        Toast.makeText(getBaseContext(), list.get(i).getPassRed(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.item1) {
            String contra = String.valueOf(editPass.getText());
            if (contra.equals("")) {
                Toast.makeText(getApplicationContext(), "Llena los campos", Toast.LENGTH_LONG).show();
            } else {
                MyData myData = new MyData();
                myData.setContra(contra);
                lista.add(myData);
                MyAdapter myAdapter = new MyAdapter(lista, getBaseContext());
                listView.setAdapter(myAdapter);
                editPass.setText("");
                Toast.makeText(getApplicationContext(), "Se agregó la contraseña", Toast.LENGTH_LONG).show();
            }
            return true;
        }

        if (id == R.id.item3) {
            Intent intent = new Intent(entrada.this, login.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
