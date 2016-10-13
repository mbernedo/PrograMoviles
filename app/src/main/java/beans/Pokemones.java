package beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by migue_000 on 09/10/2016.
 */
public class Pokemones {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("pokemones")
    @Expose
    private List<Integer> pokemones = new ArrayList<Integer>();

    public Pokemones(String id, List<Integer> pokemones) {
        this.id = id;
        this.pokemones = pokemones;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Integer> getPokemones() {
        return pokemones;
    }

    public void setPokemones(List<Integer> pokemones) {
        this.pokemones = pokemones;
    }
}
