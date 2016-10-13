package interfaces;

import beans.Atrapar;
import beans.ListPokemones;
import beans.Pokemon;
import beans.RespondePokemones;
import beans.Respuesta;
import beans.Usuario;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by migue_000 on 09/10/2016.
 */
public interface ServiceUsuario {
    @POST("/login")
    Call<Respuesta> login(@Body Usuario usuario);

    @POST("/registro")
    Call<Respuesta> registrar(@Body Usuario usuario);

    @GET("/usuario/{username}/pokemones")
    Call<RespondePokemones> getPokemones(@Path("username") String username);

    @GET("/mispokemones/{username}")
    Call<ListPokemones> getMisPokemones(@Path("username") String username);

    @GET("/pokedata/pokemones")
    Call<ListPokemones> getListPokemones();

    @POST("/addpoke")
    Call<Respuesta> registrarPokemon(@Body Atrapar atrapar);
}
