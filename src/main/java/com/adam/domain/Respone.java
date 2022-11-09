package com.adam.domain;

/**
 * @author adam
 * @auther adam
 * @date 2022/11/8
 * @apiNote com.adam.domain
 */
public class Respone<T> {
    private String code;
    private String Message;
    private T data;

    public Respone(String code, String message, T data) {
        this.code = code;
        Message = message;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
