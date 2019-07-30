package com.example.ggwebinfo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.ggwebinfo.Get10;
import com.example.ggwebinfo.R;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Main2Activity extends AppCompatActivity {

    public static String BaseURL = "https://www.greedygame.com";
    TextView info10;
    private String str;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main2 );

        info10 = (TextView) findViewById(R.id.textView123);
        get10();
    }
    void get10(){
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl((BaseURL))
                .addConverterFactory( GsonConverterFactory.create() )
                .client(okHttpClient)
                .build();

        final Get10 request = retrofit.create(Get10.class);
        Call<ResponseBody> call = request.getHtml();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    str = new String(response.body().bytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                get2020();
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                info10.setText(t.getMessage());
            }
        } );
    }

    void get2020() {
        Log.d("test",str);
        String output = "";
        output += str.charAt(10);
        info10.setText(output);
    }



}
