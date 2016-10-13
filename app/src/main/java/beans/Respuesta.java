package beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by migue_000 on 09/10/2016.
 */
public class Respuesta {
    @SerializedName("status")
    @Expose
    private Status status;
    @SerializedName("usuario")
    @Expose
    private Usuario usuario;

    public Respuesta() {
    }

    public Respuesta(Status status, Usuario user) {
        this.status = status;
        this.usuario = user;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Usuario getUser() {
        return usuario;
    }

    public void setUser(Usuario usuario) {
        this.usuario = usuario;
    }
}
