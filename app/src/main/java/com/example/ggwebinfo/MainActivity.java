package com.example.ggwebinfo;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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

    TextView Get10, GetEvery10, GetWC, T1, T2, T3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        T1 = (TextView) findViewById(R.id.textView11);
        T2 = (TextView) findViewById(R.id.textView22);
        T3 = (TextView) findViewById(R.id.textView33);

                sendMessage();
    }

    void sendMessage(){
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
                get1010();
                getCount();
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                T1.setText(t.getMessage());
            }
        } );
    }

    void get2020() {
        Log.d("test",str);
        String output = "";
        output += str.charAt(10);
        T1.setText(output);
    }
    void get1010() {
        Log.d("test",str);
        String output = "";
        for (int i = 0; i < str.length(); i++) {
            if (i % 10 == 0) {
                output = output + str.charAt(i);
            }
        }
        T2.setText(output);
    }

    private Map<String, Integer> reverseOrder(HashMap<String, Integer> wordOut) {
        List<Map.Entry<String, Integer> > list = new LinkedList<Map.Entry<String, Integer> >(wordOut.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    void getCount(){
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

        Map<String, Integer> wordOut1 = reverseOrder(wordOut);

        for(Map.Entry<String, Integer> entry : wordOut1.entrySet()) {
            String Key = entry.getKey();
            Integer Value = entry.getValue();
            output = output + "Word : " + Key + " " + "Value : " + Value + "\n";
        }
        T3.setMovementMethod(new ScrollingMovementMethod());
        T3.setText(output);
    }
}
