package beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by migue_000 on 09/10/2016.
 */
public class RespondePokemones {
    @SerializedName("status")
    @Expose
    private Status status;
    @SerializedName("pokemones")
    @Expose
    private Pokemones pokemones;

    public RespondePokemones(Status status, Pokemones pokemones) {
        this.status = status;
        this.pokemones = pokemones;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Pokemones getPokemones() {
        return pokemones;
    }

    public void setPokemones(Pokemones pokemones) {
        this.pokemones = pokemones;
    }
}
