package pe.edu.ulima.pokemonapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PuntajeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puntaje);
        TextView textView= (TextView) findViewById(R.id.txtPuntaje);
        Bundle bundle= getIntent().getExtras();
        int puntaje= bundle.getInt("puntos");
        textView.setText(String.valueOf(puntaje));
    }
}
