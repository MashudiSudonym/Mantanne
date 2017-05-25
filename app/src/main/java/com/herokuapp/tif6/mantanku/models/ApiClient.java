package com.herokuapp.tif6.mantanku.models;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by sudonym on 19/05/2017.
 */

public interface ApiClient {
    // ALL GET Parameters
    @GET("/ApiShowMantans")
    Call<ApiValue> viewAll();

    @GET("/ApiShowMantan/{id}")
    Call<ApiValue> viewId(@Path("id") Integer id);

    @GET("/ApiGetToken")
    Call<ApiValue> viewToken();

    // ALL POST Parameters
    @POST("/ApiLogout")
    Call<ApiValue> keluar();

    @FormUrlEncoded
    @POST("/ApiRegister")
    Call<ApiValue> daftar(@Field("email") String email,
                       @Field("password") String password);

    @FormUrlEncoded
    @POST("/ApiLogin")
    Call<ApiValue> masuk(@Field("email") String email,
                      @Field("password") String password);

    @FormUrlEncoded
    @POST("/ApiAddMantan")
    Call<ApiValue> tambah(@Field("nama") String nama,
                       @Field("alasan") String alasan);

    // ALL PUT Parameters
    @FormUrlEncoded
    @PUT("/ApiEditMantan/{id}")
    Call<ApiValue> edit(@Path("id") Integer id,
                     @Field("nama") String nama,
                     @Field("alasan") String alasan);

    // ALL DELETE Parameters
    @DELETE("/ApiDeleteMantan/{id}")
    Call<ApiValue> edit(@Path("id") Integer id);
}
