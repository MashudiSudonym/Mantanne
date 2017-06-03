package com.herokuapp.tif6.mantanku.models;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by sudonym on 19/05/2017.
 */

public interface ApiClient {
    // ALL GET Parameters
    @GET("/ShowUsers")
    Call<ApiValue> viewAll();

    @GET("/ShowUser/{id}")
    Call<ApiValue> viewId(@Path("id") Integer id);

    // ALL POST Parameters
//    @FormUrlEncoded
//    @POST("/AddUser")
//    Call<ApiValue> tambah(@Field("nama") String nama,
//                       @Field("alamat") String alamat);

    @Multipart
    @POST("/AddUser")
    Call<RequestBody> tambah(
            @Part("nama") String nama,
            @Part("alamat") String alamat,
            @Part("no_hp") String nohp,
            @Part MultipartBody.Part file
    );

    // ALL PUT Parameters
    @FormUrlEncoded
    @PUT("/UpdateUser/{id}")
    Call<ApiValue> edit(@Path("id") Integer id,
                     @Field("nama") String nama,
                     @Field("alamat") String alamat);

    // ALL DELETE Parameters
    @DELETE("/DeleteUser/{id}")
    Call<ApiValue> edit(@Path("id") Integer id);
}
