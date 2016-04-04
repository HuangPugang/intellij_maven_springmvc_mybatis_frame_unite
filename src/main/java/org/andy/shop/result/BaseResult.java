package org.andy.shop.result;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Paul on 16/4/4.
 */
public abstract class BaseResult {
    @SerializedName("status")
    private int status = 1;
    @SerializedName("message")
    private String message="";

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
