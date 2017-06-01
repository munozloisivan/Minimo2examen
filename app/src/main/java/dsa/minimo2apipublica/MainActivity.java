package dsa.minimo2apipublica;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class MainActivity extends AppCompatActivity {

    String tag = "Main Activity";
    int numRepos, followers, following;
    String loginUser;
    String avatar_url;
    TextView textView;
    String nombreUsuario;
    EditText username;

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
       // nombreUsuario = textView.getText().toString();
        botonMain.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                username = (EditText) findViewById(R.id.etNombreUsuario);
                nombreUsuario = username.getText().toString();

                if (nombreUsuario!=null) {
                    // Toast.makeText(MainActivity.this, nombreUsuario, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, info_seguidores.class);
                    intent.putExtra("nombre", nombreUsuario);
                    startActivity(intent);
                }
                else
                    Toast.makeText(MainActivity.this, "Introduce Usuario", Toast.LENGTH_SHORT).show();
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

