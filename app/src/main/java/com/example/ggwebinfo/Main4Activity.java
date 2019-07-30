package com.example.ggwebinfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.ggwebinfo.Get10;
import com.example.ggwebinfo.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;

import java.io.OptionalDataException;
import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Main4Activity extends AppCompatActivity {

    private TextView WC;
    public static String BaseURL = "https://www.greedygame.com/";
    private int i;
    private String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_main4);

        WC = (TextView) findViewById(R.id.textView345);
        WCount();

    }


    public void WCount(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseURL)
                .addConverterFactory( GsonConverterFactory.create() )
                .build();
        Get10 service = retrofit.create(Get10.class);

        final Get10 apiService = retrofit.create(Get10.class);
        Call<ResponseBody> stringCall = apiService.getHtml();
        stringCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String str = ((response.body().source().toString()));
                getCount();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        } );

    }

     void getCount() {
            int totalSoFar = 0;
         //   for (i = 0; i < str.length; i++)
               // if (str(i) == " ") {
                    totalSoFar += 1;
                }
          // totalSoFar += 1;

            //    WC.setText(totalSoFar);
        }

//}

