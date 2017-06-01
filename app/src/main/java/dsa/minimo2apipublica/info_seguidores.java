package dsa.minimo2apipublica;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
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

public class info_seguidores extends AppCompatActivity {

    String tag = "info_seguidores";
    String loginUser, avatar_url, nombreusuario;
    int numRepos, followers, following;

    private ProgressBar progressbar;
    private List<Follower> listaFollow;
    private List<String> listaNombres;
    private ListView listView;
    private ImageView imagen;

    String usuarioname;

    private TextView textViewRepositories,textViewFollowing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followers);

        textViewRepositories = (TextView) findViewById(R.id.tvRepositories);
        textViewFollowing = (TextView) findViewById(R.id.tvFollowing);

      /*  loginUser = getIntent().getExtras().getString("userlogin");
        numRepos = getIntent().getExtras().getInt("numrepos");
        followers = getIntent().getExtras().getInt("followers");
        following = getIntent().getExtras().getInt("following");
        avatar_url = getIntent().getExtras().getString("avatar");*/

        Bundle extradata = getIntent().getExtras();
        usuarioname = extradata.getString("nombre");

        textViewRepositories.setText("Repositories: "+numRepos);
        textViewFollowing.setText("Following: "+following);

        listView=(ListView)findViewById(R.id.lvFollowers);

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl("https://api.github.com/")
                    .addConverterFactory(GsonConverterFactory.create());
//
            Retrofit retrofit =
                    builder.client(httpClient.build()).build();

            // Create an instance of our GitHub API interface.
            Github getListaFollowers = retrofit.create(Github.class);

            // Create a call instance for looking up Retrofit contributors.
            Call<List<Follower>> call = getListaFollowers.getListFollowers(usuarioname);

            // Fetch and print a list of the contributors to the library.
            call.enqueue(new Callback<List<Follower>>() {
                @Override
                public void onResponse(Call<List<Follower>> call, Response<List<Follower>> response) {
                    progressbar = (ProgressBar) findViewById(R.id.progress);

                    if(response.code()==200) {
                        listaFollow = (List<Follower>) response.body();
                        listView = (ListView) findViewById(R.id.lvFollowers);
                        listaNombres = new ArrayList<>();
                        for (int j = 0; j < listaFollow.size(); j++) {
                            String item = listaFollow.get(j).getLogin();
                            listaNombres.add(item);
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                                (info_seguidores.this, android.R.layout.simple_list_item_1, listaNombres);
                        listView.setAdapter(arrayAdapter);
                    }
                  else {
                        Toast.makeText(info_seguidores.this, "No funciona: " + response.code(), Toast.LENGTH_SHORT).show();
                        info_seguidores.this.finish();
                    }
                    progressbar.setVisibility(ListView.GONE);
                }

                @Override
                public void onFailure(Call<List<Follower>> call, Throwable t) {
                    Toast.makeText(info_seguidores.this, "No funciona", Toast.LENGTH_SHORT).show();
                    info_seguidores.this.finish();
                }
            });
        }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(tag, "Event onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(tag, "Event onResume()");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(tag, "Event onPause()");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(tag, "Event onStop()");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(tag, "Event onRestart()");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(tag, "Event onDestroy()");

    }

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

                    ArrayAdapter adapter=new ArrayAdapter(info_seguidores.this,android.R.layout.simple_list_item_1, listaFollowers);

                    listView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(info_seguidores.this, "ERROR", Toast.LENGTH_SHORT).show();

            }
        });*/


    }

