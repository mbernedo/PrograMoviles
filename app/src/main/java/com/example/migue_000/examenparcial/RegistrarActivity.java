package com.example.migue_000.examenparcial;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import beans.Respuesta;
import beans.Usuario;
import conexion.Connection;
import interfaces.ServiceUsuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegistrarActivity extends AppCompatActivity {
    EditText eteRegUsuario;
    EditText eteRegPassword;
    EditText eteRegPassword2;
    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        eteRegUsuario = (EditText) findViewById(R.id.eteRegUsuario);
        eteRegPassword = (EditText) findViewById(R.id.eteRegPassword);
        eteRegPassword2 = (EditText) findViewById(R.id.eteRegPassword2);
        Button butGuardar = (Button) findViewById(R.id.butGuardar);
        butGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(eteRegUsuario.getText().toString().equalsIgnoreCase("") ||
                        eteRegPassword.getText().toString().equalsIgnoreCase("") ||
                        eteRegPassword2.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(RegistrarActivity.this, "Falta llenar datos", Toast.LENGTH_SHORT).show();
                }else{
                    if(eteRegUsuario.getText().toString().equalsIgnoreCase("")){
                        Toast.makeText(RegistrarActivity.this, "Utilizar otro usuario.", Toast.LENGTH_SHORT).show();
                    }else{
                        if(eteRegPassword.getText().toString().equalsIgnoreCase(eteRegPassword2.getText().toString())){
                            Connection conexion = new Connection();
                            Retrofit retrofit= conexion.getConecction();
                            ServiceUsuario usuariosService = retrofit.create(ServiceUsuario.class);
                            Usuario usuario = new Usuario();
                            usuario.setUsername(eteRegUsuario.getText().toString());
                            usuario.setPassword(eteRegPassword.getText().toString());
                            progress = new ProgressDialog(RegistrarActivity.this);
                            progress.setTitle("Loading");
                            progress.setMessage("Registrando usuario");
                            progress.setCancelable(false);
                            progress.show();
                            Call<Respuesta> usuarioCall = usuariosService.registrar(usuario);
                            usuarioCall.enqueue(new Callback<Respuesta>() {
                                @Override
                                public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {
                                    Respuesta respuesta = response.body();
                                    if(respuesta.getStatus().getCod()==1){
                                        Toast.makeText(RegistrarActivity.this, respuesta.getStatus().getMsg(), Toast.LENGTH_SHORT).show();
                                        Intent intent= new Intent(RegistrarActivity.this,LoginActivity.class);
                                        progress.dismiss();
                                        startActivity(intent);
                                    }else{
                                        Toast.makeText(RegistrarActivity.this, respuesta.getStatus().getMsg(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                                @Override
                                public void onFailure(Call<Respuesta> call, Throwable t) {
                                    progress.dismiss();
                                    Toast.makeText(RegistrarActivity.this, "Hubo un problema en la conexión", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }else{
                            Toast.makeText(RegistrarActivity.this, "Las contraseñas ingresadas con coinciden", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }
}
