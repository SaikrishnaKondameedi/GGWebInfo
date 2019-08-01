package com.example.ggwebinfo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Main3Activity extends AppCompatActivity {

    public static String BaseURL = "https://www.greedygame.com";

    TextView every10;
    private String str;
    private okhttp3.Response response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main3 );
        every10 = (TextView) findViewById( R.id.textView223 );
        getEvery10();
    }

    void getEvery10() {

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl( BaseURL )
                .addConverterFactory( GsonConverterFactory.create() )
                .client( okHttpClient )
                .build();

        final Get10 request = retrofit.create( Get10.class );
        Call<ResponseBody> call = request.getHtml();
        call.enqueue( new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    str = new String(response.body().bytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                get1010();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                every10.setText( t.getMessage() );
            }
        } );
    }

    void get1010() {
        Log.d("test",str);
        String output = "";
        for (int i = 0; i < str.length(); i++) {
            if (i % 10 == 0) {
                output = output + str.charAt(i);
            }
        }
        every10.setText(output);
    }

}

