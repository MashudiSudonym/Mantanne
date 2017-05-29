package com.herokuapp.tif6.mantanku.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sudonym on 19/05/2017.
 */

public class ApiResult {
    @SerializedName("alamat")
    @Expose
    private String alamat;

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("nama")
    @Expose
    private String nama;

    @SerializedName("no_hp")
    @Expose
    private String no_hp;

    @SerializedName("photo")
    @Expose
    private String photo;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getNoHp() {
        return no_hp;
    }

    public void setNoHp(String no_hp) {
        this.no_hp = no_hp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
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
}
