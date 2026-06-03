package com.example.lab20;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient_mouad {

    private static final String BASE_URL_mouad = "http://10.0.2.2/numberbook-api/api/";
    private static Retrofit retrofit_mouad;

    public static Retrofit getClient_mouad() {
        if (retrofit_mouad == null) {
            retrofit_mouad = new Retrofit.Builder()
                    .baseUrl(BASE_URL_mouad)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit_mouad;
    }
}
