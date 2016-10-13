package conexion;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by migue_000 on 09/10/2016.
 */
public class Connection {
    public static final String BASE_URL = "https://dry-reaches-65547.herokuapp.com";

    public Retrofit getConecction(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return  retrofit;
    }
}
