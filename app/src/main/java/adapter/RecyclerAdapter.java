package adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.migue_000.examenparcial.CapturarActivity;
import com.example.migue_000.examenparcial.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import beans.Pokemon;

/**
 * Created by migue_000 on 09/10/2016.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.PokeHolder> {
    private ArrayList<Pokemon> mPokemones;
    private Context context;
    private String username;

    public RecyclerAdapter(ArrayList<Pokemon> mPokemones,Context context,String username) {
        this.mPokemones = mPokemones;
        this.context= context;
        this.username=username;
    }

    @Override
    public RecyclerAdapter.PokeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pokemon, parent, false);
        return new PokeHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.PokeHolder holder, int position) {

        holder.tviNombrePokemon.setText(mPokemones.get(position).getName());
        Picasso.with(context).load(mPokemones.get(position).getImg()).into(holder.mImagenPokemon);
        holder.url = mPokemones.get(position).getImg();
        holder.idPoke=mPokemones.get(position).getId();
        holder.username=username;

        holder.nombrePoke=mPokemones.get(position).getName();
        holder.tipo=mPokemones.get(position).getType();
        holder.nivel=mPokemones.get(position).getNivel();
        holder.descripcion=mPokemones.get(position).getDescription();
    }

    @Override
    public int getItemCount() {
        return mPokemones.size();
    }

    public static class PokeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mImagenPokemon;
        private TextView tviNombrePokemon;
        private String url="";
        private int idPoke=0;
        private String username;
        private String nombrePoke;
        private String tipo;
        private int nivel;
        private String descripcion;
        public PokeHolder(View v) {
            super(v);
            mImagenPokemon = (ImageView) v.findViewById(R.id.imgPoke);
            tviNombrePokemon = (TextView) v.findViewById(R.id.tviNombrePoke);
            v.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            Context context = itemView.getContext();
            Intent intent = new Intent(context, CapturarActivity.class);
            intent.putExtra("url",url);
            intent.putExtra("idPoke",idPoke);
            intent.putExtra("username",username);
            intent.putExtra("nombrepoke",nombrePoke);
            intent.putExtra("tipo",tipo);
            intent.putExtra("nivel",nivel);
            intent.putExtra("descripcion",descripcion);
            context.startActivity(intent);
        }
    }
}
