package com.example.migue_000.examenparcial;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import beans.ListPokemones;
import beans.Pokemon;
import beans.RespondePokemones;
import conexion.Connection;
import interfaces.ServiceUsuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PokemonesActivity extends AppCompatActivity {
    TextView tviNivel;
    TextView tviTipo;
    TextView tviDescripcion;
    TextView tviPokemon;
    ImageView img;
    int i=0;
    Button butSiguiente;
    Button butAtras;
    String username="";
    List<Pokemon> listaPok;
    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemones);
        tviPokemon= (TextView) findViewById(R.id.tviPokemon);
        tviNivel = (TextView) findViewById(R.id.tviNivel);
        tviTipo = (TextView) findViewById(R.id.tviTipo);
        tviDescripcion = (TextView) findViewById(R.id.tviDescripcion);
        img = (ImageView) findViewById(R.id.img);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            username = extras.getString("username");
        }
        butSiguiente= (Button) findViewById(R.id.butSiguiente);
        butAtras = (Button) findViewById(R.id.butAtras);
        Connection conexion = new Connection();
        Retrofit retrofit = conexion.getConecction();
        final ServiceUsuario usuariosService = retrofit.create(ServiceUsuario.class);
        progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Cargando pokemones");
        progress.setCancelable(false);
        progress.show();
        usuariosService.getMisPokemones(username).enqueue(new Callback<ListPokemones>() {
            @Override
            public void onResponse(Call<ListPokemones> call, Response<ListPokemones> response) {
                final ListPokemones listPokemones = response.body();
                listaPok = listPokemones.getPokemones();
                if(listaPok.size()==0){
                    progress.dismiss();
                    Intent intent = new Intent(PokemonesActivity.this, DashboardActivity.class);
                    startActivity(intent);
                    Toast.makeText(PokemonesActivity.this, "Aún no tienes pokemones. Ve a capturar algunos", Toast.LENGTH_SHORT).show();
                }else{
                    cargarInformacion();
                    progress.dismiss();
                }
            }
            @Override
            public void onFailure(Call<ListPokemones> call, Throwable t) {
                progress.dismiss();
                Intent intent = new Intent(PokemonesActivity.this, DashboardActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
                Toast.makeText(PokemonesActivity.this, "Hubo un error en la conexión", Toast.LENGTH_SHORT).show();
            }
        });
        butSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i==listaPok.size()-1){
                    Toast.makeText(PokemonesActivity.this, "Ya no hay mas pokemones", Toast.LENGTH_SHORT).show();
                }else{
                    i++;
                    cargarInformacion();
                }
            }
        });
        butAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i==0){
                    Toast.makeText(PokemonesActivity.this, "Presionar >>", Toast.LENGTH_SHORT).show();
                }else{
                    i--;
                    cargarInformacion();
                }
            }
        });
    }
    public void Menu(View v){
        Intent intent = new Intent(PokemonesActivity.this,DashboardActivity.class);
        intent.putExtra("username",username);
        startActivity(intent);
    }
    public void cargarInformacion(){
        Log.i("MisPokemonesActivity",listaPok.get(i).getImg());
        Picasso.with(this).load(listaPok.get(i).getImg()).into(img);
        tviPokemon.setText(listaPok.get(i).getName().toString());
        tviNivel.setText( listaPok.get(i).getNivel().toString());
        tviTipo.setText(listaPok.get(i).getType());
        tviDescripcion.setText(listaPok.get(i).getDescription());
    }
}
