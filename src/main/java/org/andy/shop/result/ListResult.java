package org.andy.shop.result;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Paul on 16/4/4.
 */
public class ListResult {

    @SerializedName("list")
    private Object list;

    public Object getList() {
        return list;
    }

    public void setList(Object list) {
        this.list = list;
    }
}
