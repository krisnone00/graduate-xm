package com.example.exception;

public class CustomException extends RuntimeException {
    private int code;  // 改为int
    private String msg;

    public CustomException(int code, String msg) {  // 构造方法也改
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
