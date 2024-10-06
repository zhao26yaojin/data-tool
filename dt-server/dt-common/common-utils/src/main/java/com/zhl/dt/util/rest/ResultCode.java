package com.zhl.dt.util.rest;

public enum ResultCode {

    SUCCESS(200, "执行成功"),

    ERROR(500, "内部异常");

    private int code;

    private String message;

    ResultCode(int code, String message) {
        this.code = code;

        this.message = message;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

}