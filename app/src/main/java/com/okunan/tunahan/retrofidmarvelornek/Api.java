package com.okunan.tunahan.retrofidmarvelornek;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by TUNAHAN on 03.01.2018.
 */

public interface Api {
    public static final String BASE_URL="https://www.simplifiedcoding.net/demos/";

    @GET("marvel")
    Call<List<Hero>> getHeroes();
}
