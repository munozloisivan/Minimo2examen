package dsa.minimo2apipublica;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Followers extends AppCompatActivity {

    String tag = "Followers";
    String loginUser;
    int numRepos, followers, following;
    String avatar_url;

    List<Usuario> listaFollowers = new ArrayList<>();

    String nombreusuario;
    ListView listView;

    private TextView textViewRepositories,textViewFollowing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followers);

        textViewRepositories = (TextView) findViewById(R.id.tvRepositories);
        textViewFollowing = (TextView) findViewById(R.id.tvFollowing);

        loginUser = getIntent().getExtras().getString("userlogin");
        numRepos = getIntent().getExtras().getInt("numrepos");
        followers = getIntent().getExtras().getInt("followers");
        following = getIntent().getExtras().getInt("following");
        avatar_url = getIntent().getExtras().getString("avatar");

        nombreusuario = getIntent().getExtras().getString("nombre");

        textViewRepositories.setText("Repositories: "+numRepos);
        textViewFollowing.setText("Following: "+following);



        //
        listView=(ListView)findViewById(R.id.lvFollowers);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit.Builder builder = new Retrofit.Builder()
                //.baseUrl("http://10.0.2.2:8080")                //poner esta para atacar a la api nuestra 10.0.2.2
                .baseUrl("https://true-monitor.aaaida.com")       //backup
                .addConverterFactory(GsonConverterFactory.create());
//
        final Retrofit retrofit =
                builder
                        .client(
                                httpClient.build()
                        )
                        .build();

            //original examen

        final Github followers = retrofit.create(Github.class);

        Call<List<Usuario>> call = followers.getFollowers(nombreusuario);

        call.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                if (response.isSuccessful()){
                    List<Usuario> usuarioList = (List<Usuario>) response.body();

                    for (Usuario u : usuarioList){
                        listaFollowers.add(u);
                    }
                    System.out.println("TAMAÑO LISTA: "+listaFollowers.size());

                    ArrayAdapter adapter=new ArrayAdapter(Followers.this,android.R.layout.simple_list_item_1, listaFollowers);

                    listView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                Toast.makeText(Followers.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        //backup

    /*    final Github followers = retrofit.create(Github.class);

        Call<List<User>> call = followers.getFollowersBackup();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()){
                    List<User> userList = (List<User>) response.body();

                    for (User u : userList){
                        listaFollowers.add(u);
                    }
                    System.out.println("TAMAÑO LISTA: "+listaFollowers.size());

                    ArrayAdapter adapter=new ArrayAdapter(Followers.this,android.R.layout.simple_list_item_1, listaFollowers);

                    listView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(Followers.this, "ERROR", Toast.LENGTH_SHORT).show();

            }
        });*/


    }
}
