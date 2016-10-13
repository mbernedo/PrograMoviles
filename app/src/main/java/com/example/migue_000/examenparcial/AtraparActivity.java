package com.example.migue_000.examenparcial;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import adapter.RecyclerAdapter;
import beans.ListPokemones;
import beans.Pokemon;
import conexion.Connection;
import interfaces.ServiceUsuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AtraparActivity extends AppCompatActivity {
    private GridLayoutManager mGridLayoutManager;
    private RecyclerView mRecyclerView;
    private RecyclerAdapter mAdapter;
    private String username="";
    ProgressDialog progress;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atrapar);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeLayout);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            username = extras.getString("username");
        }
        mRecyclerView = (RecyclerView) findViewById(R.id.recycleView);
        mRecyclerView.setHasFixedSize(true);
        if(getResources().getConfiguration().orientation==1){
            mGridLayoutManager = new GridLayoutManager(AtraparActivity.this, 4);
        }else{
            mGridLayoutManager = new GridLayoutManager(AtraparActivity.this, 6);
        }
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        Connection conexion = new Connection();
        Retrofit retrofit = conexion.getConecction();
        final ServiceUsuario usuariosService = retrofit.create(ServiceUsuario.class);
        progress = new ProgressDialog(AtraparActivity.this);
        progress.setTitle("Loading");
        progress.setMessage("Cargando pokemones");
        progress.setCancelable(false);
        progress.show();
        usuariosService.getListPokemones().enqueue(new Callback<ListPokemones>() {
            @Override
            public void onResponse(Call<ListPokemones> call, Response<ListPokemones> response) {
                ListPokemones listPokemones = response.body();
                mAdapter = new RecyclerAdapter((ArrayList<Pokemon>)listPokemones.getPokemones(),AtraparActivity.this,username);
                mRecyclerView.setAdapter(mAdapter);
                progress.dismiss();
            }
            @Override
            public void onFailure(Call<ListPokemones> call, Throwable t) {
                progress.dismiss();
                Intent intent = new Intent(AtraparActivity.this, DashboardActivity.class);
                startActivity(intent);
                Toast.makeText(AtraparActivity.this, "Hubo un error en la conexión", Toast.LENGTH_SHORT).show();
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                usuariosService.getListPokemones().enqueue(new Callback<ListPokemones>() {
                    @Override
                    public void onResponse(Call<ListPokemones> call, Response<ListPokemones> response) {
                        ListPokemones listPokemones = response.body();
                        mAdapter = new RecyclerAdapter((ArrayList<Pokemon>)listPokemones.getPokemones(),AtraparActivity.this,username);
                        mRecyclerView.setAdapter(mAdapter);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                    @Override
                    public void onFailure(Call<ListPokemones> call, Throwable t) {
                        Intent intent = new Intent(AtraparActivity.this, DashboardActivity.class);
                        startActivity(intent);
                        Toast.makeText(AtraparActivity.this, "Hubo un error en la conexión", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

    }
}
