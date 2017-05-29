package com.herokuapp.tif6.mantanku.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sudonym on 19/05/2017.
 */

public class ApiValue {
    @SerializedName("Message")
    @Expose
    String message;

    public List<ApiResult> result;

    public String getMessage() {
        return message;
    }

    public List<ApiResult> getResult(){
        return result;
    }
}
