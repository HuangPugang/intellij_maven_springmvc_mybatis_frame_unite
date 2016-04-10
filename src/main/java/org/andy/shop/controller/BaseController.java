package org.andy.shop.controller;

import com.google.gson.Gson;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;
import org.andy.common.StatusCode;
import org.andy.shop.result.ListResult;
import org.andy.shop.result.ResponseListResult;
import org.andy.shop.result.ResponseResult;

/**
 * Created by Paul on 16/4/9.
 */
public abstract class BaseController<T> {

    protected String generateError(String msg){
        ResponseResult result = new ResponseResult();
        result.setStatus(StatusCode.ERROR.getCode());
        result.setMessage(msg);
        Gson gson = new Gson();
        return gson.toJson(result);
    };


    protected String generateResult(Object object){
        ResponseResult result = new ResponseResult();
        result.setStatus(StatusCode.SUCCESS.getCode());
        result.setData(object);
        Gson gson = new Gson();
        return gson.toJson(result);
    }


    protected String generateResult(java.util.List<?> list){
        ResponseListResult result = new ResponseListResult();
        ListResult listResult = new ListResult();
        listResult.setList(list);
        result.setObject(listResult);
        Gson gson = new Gson();
        return gson.toJson(result);
    }

}
