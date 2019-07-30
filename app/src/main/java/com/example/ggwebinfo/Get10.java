package com.example.ggwebinfo;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Get10 {

    @GET(".")
    Call<ResponseBody> getHtml();
}

