package com.example.migue_000.examenparcial;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import beans.Respuesta;
import beans.Usuario;
import conexion.Connection;
import interfaces.ServiceUsuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {
    ProgressDialog progress;
    EditText eteUsuario;
    EditText etePassword;
    String username="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        eteUsuario = (EditText) findViewById(R.id.eteUsuario);
        etePassword = (EditText) findViewById(R.id.etePassword);
        Button butLogin = (Button) findViewById(R.id.butLogin);
        butLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (eteUsuario.getText().toString().equalsIgnoreCase("") ||
                        etePassword.getText().toString().equalsIgnoreCase("")) {
                    Toast.makeText(LoginActivity.this, "No completó todos los campos requeridos", Toast.LENGTH_SHORT).show();
                } else {
                    Connection conexion = new Connection();
                    Retrofit retrofit = conexion.getConecction();
                    ServiceUsuario usuariosService = retrofit.create(ServiceUsuario.class);
                    Usuario usuario = new Usuario();
                    usuario.setUsername(eteUsuario.getText().toString());
                    usuario.setPassword(etePassword.getText().toString());
                    Call<Respuesta> usuarioCall = usuariosService.login(usuario);
                    progress = new ProgressDialog(LoginActivity.this);
                    progress.setTitle("Loading");
                    progress.setMessage("Verificando usuario");
                    progress.setCancelable(false);
                    progress.show();
                    usuarioCall.enqueue(new Callback<Respuesta>() {
                        @Override
                        public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {
                            Respuesta r = response.body();
                            if (r.getStatus().getCod() == 1) {
                                username = r.getUser().getUsername();
                                Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                                intent.putExtra("username", username);
                                progress.dismiss();
                                startActivity(intent);
                            } else {
                                progress.dismiss();
                                Toast.makeText(LoginActivity.this, r.getStatus().getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onFailure(Call<Respuesta> call, Throwable t) {
                            progress.dismiss();
                            Toast.makeText(LoginActivity.this, "Hubo un error en la conexión", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
    public void Registrar(View v){
        Intent intent = new Intent(this, RegistrarActivity.class);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
