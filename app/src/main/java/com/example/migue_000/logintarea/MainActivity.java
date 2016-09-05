package com.example.migue_000.logintarea;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText nombre;
    EditText pass;
    TextView mensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nombre = (EditText) findViewById(R.id.edtNombre);
        pass = (EditText) findViewById(R.id.edtPass);
        mensaje = (TextView) findViewById(R.id.txtmensaje);
    }

    public void Ingresar(View view){
        String user = nombre.getText().toString();
        String clave = pass.getText().toString();
        if(user.equalsIgnoreCase("pi") && clave.equalsIgnoreCase("123")){
            mensaje.setText("Ingreso correcto");
        }else{
            mensaje.setText("Ingreso incorrecto");
        }
    }
}
