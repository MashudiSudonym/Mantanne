package com.herokuapp.tif6.mantanku.services;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sudonym on 19/05/2017.
 */

public class ServiceGenerator {
    // Base URL
    private static final String BASE_URL = "https://floating-garden-85536.herokuapp.com/";

    // Retrofit Builder
    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(
                            GsonConverterFactory.create()
                    );

    private static Retrofit retrofit = builder.build();

    // Logging Http
    private static HttpLoggingInterceptor logging =
            new HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY);

    // OkHttpClient Builder
    private static OkHttpClient.Builder httpClient =
            new OkHttpClient.Builder();

    // createService Class for Logging Http
    public static <S> S createService(Class<S> serviceClass) {
        // Logging Http
        if (!httpClient.interceptors().contains(logging)) {
            httpClient.addInterceptor(logging);
            builder.client(httpClient.build());
            retrofit = builder.build();
        }

        return retrofit.create(serviceClass);
    }
}
