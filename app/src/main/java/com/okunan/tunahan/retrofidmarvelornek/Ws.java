package com.okunan.tunahan.retrofidmarvelornek;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by TUNAHAN on 03.01.2018.
 */

public class Ws {
    public static Api getService(){
        Gson gson=new GsonBuilder().setLenient().create();

        OkHttpClient client=new OkHttpClient();

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();

        return retrofit.create(Api.class);
    }
}
