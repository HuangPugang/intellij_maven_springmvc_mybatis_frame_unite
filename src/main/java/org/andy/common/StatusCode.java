package org.andy.common;

/**
 * Created by Paul on 16/4/9.
 */
public enum StatusCode {
    ERROR("错误", -1), SUCCESS("成功",0);


    // 成员变量
    private String msg;
    private int code;

    // 构造方法
    StatusCode(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }


    public int getCode() {
        return code;
    }
    public String getMsg(){
        return msg;
    }
}