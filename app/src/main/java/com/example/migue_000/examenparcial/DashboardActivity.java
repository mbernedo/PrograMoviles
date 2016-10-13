package com.example.migue_000.examenparcial;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.support.v7.app.AlertDialog;
import android.widget.Button;

public class DashboardActivity extends AppCompatActivity {
    String username="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Button butMisPokemones= (Button) findViewById(R.id.butMisPokemones);
        Button butPokemonesDisponibles = (Button) findViewById(R.id.butPokemonesDisponibles);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            username = extras.getString("username");
        }
        butMisPokemones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this,PokemonesActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });
        butPokemonesDisponibles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this,AtraparActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("¿Deseas salir?")
                .setMessage("¿Estas seguro?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        moveTaskToBack(true);
                    }
                }).create().show();
    }
}
