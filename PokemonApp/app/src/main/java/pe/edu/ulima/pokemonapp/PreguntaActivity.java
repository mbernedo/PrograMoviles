package pe.edu.ulima.pokemonapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import pe.edu.ulima.pokemonapp.model.GestorPokemonApp;
import pe.edu.ulima.pokemonapp.model.Pregunta;

public class PreguntaActivity extends AppCompatActivity {
    ImageView imageView;
    TextView textView1;
    TextView textView2;
    EditText editText;
    ImageButton imageButton1;
    ImageButton imageButton2;
    List<Pregunta> lista = GestorPokemonApp.getInstance().obtenerPreguntas();
    int i=0;
    private int puntaje=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(savedInstanceState!=null){
            i= savedInstanceState.getInt("posicion");
            puntaje= savedInstanceState.getInt("puntaje");
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregunta);
        Bundle bundle= getIntent().getExtras();
        String user= bundle.getString("user");
        imageView= (ImageView) findViewById(R.id.imgPok);
        textView1= (TextView) findViewById(R.id.txtNumero);
        textView2= (TextView) findViewById(R.id.txtPregunta);
        editText= (EditText) findViewById(R.id.edtRespuesta);
        imageButton1= (ImageButton) findViewById(R.id.butOk);
        imageButton2= (ImageButton) findViewById(R.id.butNext);
        textView1.setText(String.format("Pregunta %s", lista.get(i).getId()));
        textView2.setText(lista.get(i).getPregunta());

    }
    public void Validar(View v){
            if(editText.getText().toString().equalsIgnoreCase(lista.get(i).getRespuesta())){
                imageView.setImageResource(lista.get(i).getImagen());
                Toast toast = Toast.makeText(this, "Respuesta correcta", Toast.LENGTH_SHORT);
                toast.show();
                puntaje+=10;
            }else{
                Toast toast1=Toast.makeText(this, "Respuesta incorrecta", Toast.LENGTH_SHORT);
                toast1.show();
            }
    }
    public void Siguiente(View v){
        i++;
        if(i==lista.size()){
            Intent intent= new Intent(this, PuntajeActivity.class);
            intent.putExtra("puntos", puntaje);
            startActivity(intent);
        }else{
            textView1.setText(String.format("Pregunta %s", lista.get(i).getId()));
            textView2.setText(lista.get(i).getPregunta());
            imageView.setImageResource(R.drawable.pokeball);
            editText.setText("");
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("posicion", i);
        savedInstanceState.putInt("puntaje", puntaje);
        super.onSaveInstanceState(savedInstanceState);
    }
}
