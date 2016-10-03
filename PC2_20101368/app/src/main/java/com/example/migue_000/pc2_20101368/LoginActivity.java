package com.example.migue_000.pc2_20101368;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    EditText user;
    EditText pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user = (EditText) findViewById(R.id.eteUser);
        pass = (EditText) findViewById(R.id.etePass);
    }
    public void Ingresar(View v){
        Intent intent = new Intent(this,RegistroActivity.class);
    }
    public void Registrar(View v){
        Intent intent2 = new Intent(this, RegistroActivity.class);
        startActivity(intent2);
    }
}
