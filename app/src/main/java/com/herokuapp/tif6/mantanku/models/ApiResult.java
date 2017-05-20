package com.herokuapp.tif6.mantanku.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sudonym on 19/05/2017.
 */

public class ApiResult {
    @SerializedName("Alasan Putus")
    @Expose
    private String alasan;

    @SerializedName("Id")
    @Expose
    private Integer id;

    @SerializedName("Nama")
    @Expose
    private String nama;

    @SerializedName("Message")
    @Expose
    private String message;

    public String getAlasan() {
        return alasan;
    }

    public void setAlasan(String alasan) {
        this.alasan = alasan;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return nama;
    }

    public void setName(String nama) {
        this.nama = nama;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
