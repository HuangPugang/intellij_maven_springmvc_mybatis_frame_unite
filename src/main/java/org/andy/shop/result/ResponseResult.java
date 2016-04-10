package org.andy.shop.result;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Paul on 16/4/4.
 */
public class ResponseResult extends BaseResult{
    @SerializedName("data")
    private Object data ;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
