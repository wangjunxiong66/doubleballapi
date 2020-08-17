package com.doubleball.protocol;

import com.doubleball.enums.ResponseCode;

/**
 * @author wjx
 * @version 1.0
 * @date 2020/8/14 上午11:15
 */
public class ServiceResult<T> {

    private int code;
    private String message;
    private T bean;

    public ServiceResult(){
    }

    public ServiceResult(int code,String message){
        this.code = code;
        this.message = message;
    }

    public ServiceResult(int code,String message,T bean){
        this.code = code;
        this.message = message;
        this.bean = bean;
    }

    public int getCode() {
        return code;
    }

    public ServiceResult setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ServiceResult setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getBean() {
        return bean;
    }

    public ServiceResult setBean(T bean) {
        this.bean = bean;
        return this;
    }

    public static ServiceResult ok(){
        return new ServiceResult(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMessage());
    }

    public static <T> ServiceResult success(T bean){
        return new ServiceResult(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMessage(),bean);
    }

    public static ServiceResult failure(int code,String message){
        return new ServiceResult(code,message);
    }

    public static <T> ServiceResult failure(int code,String message,T bean){
        return new ServiceResult(code,message,bean);
    }

    //参数非法
    public static ServiceResult invalidParam() {
        return new ServiceResult(ResponseCode.INVALID_PARAM.getCode(), ResponseCode.INVALID_PARAM.getMessage());
    }
    public static ServiceResult invalidParam(String message) {
        return new ServiceResult(ResponseCode.INVALID_PARAM.getCode(), message);
    }

}
