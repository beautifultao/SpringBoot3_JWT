package com.example.backend.entity;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONWriter;

public record RestBean<T>(int code, T data, String message) {
    public static <T> RestBean<T> success(T data) {
        return new RestBean<>(200, data, "请求成功");
    }

    public static <T> RestBean<T> success(){
        return success(null);
    }

    public static <T> RestBean<T> fail(int code, String message){
        return new RestBean<>(code, null, message);
    }

    // 把此类对象转换为json数组返回，并且允许data值为null
    public String asJsonString(){
        return JSONObject.toJSONString(this, JSONWriter.Feature.WriteNulls);
    }
}
