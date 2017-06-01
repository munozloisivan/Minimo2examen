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

    @GET("/users/{username}")
    Call<Follower> getUsuario(
            @Path("username") String username);


    @GET("/users/{username}/repos")
    Call<List<Follower>> getRepos(@Path("username") String username);

    @GET("/users/{name}/followers")
    Call<List<Follower>> getListFollowers(@Path("name") String name);

    //por si acaso
   //@GET("/api/Users")
  //  Call<List<User>> getFollowersBackup();


}
