package org.andy.shop.result;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Paul on 16/4/4.
 */
public class RespResult<T> {
    @SerializedName("status")
    private int status = 1;
    @SerializedName("message")
    private String message="";
    @SerializedName("data")
    private T data;

    public RespResult(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

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
