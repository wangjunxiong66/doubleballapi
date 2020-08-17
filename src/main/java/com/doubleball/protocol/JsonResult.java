package com.doubleball.protocol;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wjx
 * @version 1.0
 * @date 2020/8/13 下午12:50
 */
public class JsonResult implements Serializable {
    // Serializable接口是启用其序列化功能的接口。实现java.io.Serializable 接口的类是可序列化的。没有实现此接口的类将不能使它们的任意状态被序列化或逆序列化。
    private int code;   //返回码 非0即失败
    private String message; //消息提示
    private Map<String, Object> data; //返回的数据

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public JsonResult(){};

    public JsonResult(int code, String message, Map<String, Object> data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static String success(Map<String, Object> data) {
        return JSON.toJSONString(new JsonResult(0, "解析成功", data));
    }

    public static String success() {

        return success(new HashMap<String, Object>(0));
    }

    public static String failed(int code, String msg) {
        return JSON.toJSONString(new JsonResult(code, msg, new HashMap<String, Object>(0)));
    }

    public static String failed(String msg) {

        return failed(-1, msg);
    }

    public static String failed() {

        return failed("解析失败");
    }
}
