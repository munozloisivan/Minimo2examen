package dsa.minimo2apipublica;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class MainActivity extends AppCompatActivity {

    String tag = "Main Activity";
    List<Contributor> contributors = new ArrayList<Contributor>();
    List<String> listContributors = new ArrayList<>();
    ListView listView;

    int numRepos, followers, following;
    String loginUser;
    String avatar_url;
    TextView textView;
    String nombreUsuario;

    private Button botonMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.tvNombreUsuario);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit.Builder builder = new Retrofit.Builder()
                               .baseUrl("https://api.github.com/")
                                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit =
                              builder
                                               .client(
                                              httpClient.build()
                                              )
                                       .build();



        // Create an instance of our GitHub API interface.
        final Github github = retrofit.create(Github.class);

        botonMain = (Button) findViewById(R.id.btn_Main);

        botonMain.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                nombreUsuario = textView.getText().toString();
                Call<Usuario> call = github.getUsuario(nombreUsuario);
                call.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response){
                        Usuario usuario = new Usuario();
                        usuario = (Usuario) response.body();
                        System.out.println("*********");
                        System.out.println("numero repos = "+usuario.public_repos);
                        numRepos = usuario.public_repos;
                        System.out.println("followers = "+usuario.followers);
                        followers = usuario.followers;
                        System.out.println("following = "+usuario.following);
                        following = usuario.following;
                        System.out.println("login"+usuario.login);
                        loginUser = usuario.login;
                        System.out.println("url avatar: "+usuario.avatar_url);
                        avatar_url = usuario.avatar_url;

                        Intent intent = new Intent(MainActivity.this, Followers.class);
                        intent.putExtra("userlogin", loginUser);
                        intent.putExtra("numrepos",numRepos);
                        intent.putExtra("followers",followers);
                        intent.putExtra("following",following);
                        intent.putExtra("avatar",avatar_url);
                        intent.putExtra("nombre",nombreUsuario);
                        startActivity(intent);
                    }
                    @Override
                    public void onFailure(Call call, Throwable t){
                        Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                    }
                });
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
    }

