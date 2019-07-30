package com.example.ggwebinfo;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.TextView;
import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    public static String BaseURL = "https://www.greedygame.com";
    private String str;

    TextView Get10;
    TextView GetEvery10;
    TextView GetWC;
    TextView tool1;
    TextView tool2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Get10 = (TextView) findViewById(R.id.textView1);
        GetEvery10 = (TextView) findViewById(R.id.textView2);
        GetWC = (TextView) findViewById(R.id.textView3);
        tool1 = (TextView) findViewById(R.id.textView);
        tool2 = (TextView) findViewById(R.id.textView4);


        Get10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class );
                startActivity(intent);

            }
        });
        GetEvery10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Main3Activity.class );
                startActivity(intent);
            }
        });
        GetWC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Main4Activity.class );
                startActivity(intent);
            }
        });

    }

}