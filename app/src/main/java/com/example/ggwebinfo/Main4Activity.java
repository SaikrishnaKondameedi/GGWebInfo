package com.example.ggwebinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Main4Activity extends AppCompatActivity {

    private TextView WC;
    public static String BaseURL = "https://www.greedygame.com";
    private String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        WC = (TextView) findViewById(R.id.textView345);
        WCount();
    }
    public void WCount() {
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        final Get10 apiService = retrofit.create(Get10.class);

        Call<ResponseBody> stringCall = apiService.getHtml();
        stringCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    str = new String(response.body().bytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                getCount();
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                WC.setText(t.getMessage());
            }
        } );
    }
    void getCount() {
        String output = "";
        String sArr[] = str.split(" ");
        HashMap<String, Integer> wordOut = new HashMap<String, Integer>();

        for (int i = 0; i < sArr.length; i++) {
            String word = sArr[i];
            if (wordOut.containsKey(word)) {
                wordOut.put(word, wordOut.get(word) + 1);
            } else {
                wordOut.put(word, 1);
            }
        }
        for(Map.Entry<String, Integer> entry : wordOut.entrySet()) {
                String Key = entry.getKey();
                Integer Value = entry.getValue();
            output = output + "Word : " + Key + " " + "Value : " + Value + "\n";
            // Collections.sort(output, Collections.reverseOrder()); Will this work ??
        }
        WC.setText(output);
    }
}