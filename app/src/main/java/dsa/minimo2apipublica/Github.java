package dsa.minimo2apipublica;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by ivanm on 31/05/2017.
 */

public interface Github {

    @GET("/repos/{owner}/{repo}/contributors")
    Call<List<Contributor>> contributors(
            @Path("owner") String owner,
            @Path("repo") String repo);

    @GET("/users/{username}")
    Call<Usuario> getUsuario(
            @Path("username") String username);

    @GET("/users/{username}/repos")
    Call<List<Usuario>> getFollowers(
            @Path("username") String username);




    //por si acaso
    @GET("/api/Users")
    Call<List<User>> getFollowersBackup();


}
