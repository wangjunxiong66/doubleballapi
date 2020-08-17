package com.doubleball.enums;

/**
 * @author wjx
 * @version 1.0
 * @date 2020/8/14 上午11:28
 */
public enum ResponseCode {
    SUCCESS(1,"OK"),
    INVALID_PARAM(400, "请求参数不合法"),
    ;

    private int code;
    private String message;

    ResponseCode(int code,String message){
        this.code = code;
        this.message = message;
    }

    public int getCode(){
        return code;
    }

    public String getMessage(){
        return message;
    }

}
