package org.andy.shop.result;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Paul on 16/4/4.
 */
public class ResponseListResult extends BaseResult{
    @SerializedName("data")
    private ListResult object;

    public Object getObject() {
        return object;
    }

    public void setObject(ListResult object) {
        this.object = object;
    }
}
