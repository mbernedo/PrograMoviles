package pe.edu.ulima.pokemonapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import pe.edu.ulima.pokemonapp.model.Pregunta;

public class LoginActivity extends AppCompatActivity {
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editText= (EditText) findViewById(R.id.edtUser);
    }

    public void Ingresar(View v){
        String usuario = editText.getText().toString();
        Intent intent= new Intent(this, PreguntaActivity.class);
        intent.putExtra("user", usuario);
        startActivity(intent);
    }
}
