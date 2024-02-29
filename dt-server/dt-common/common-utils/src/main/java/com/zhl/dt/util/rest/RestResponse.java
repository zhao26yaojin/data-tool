package com.zhl.dt.util.rest;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Schema(description = "返回实体类")
@Data
public class RestResponse<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    int code;

    String message;

    private T result;

    private RestResponse(int code, String message, T result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }

    private RestResponse(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    private RestResponse(ResultCode resultCode, T result) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.result = result;
    }

    public static <T> RestResponse<T> ok() {
        return new RestResponse<>(ResultCode.SUCCESS);
    }

    public static <T> RestResponse<T> ok(T data) {
        return new RestResponse<>(ResultCode.SUCCESS, data);
    }

    public static <T> RestResponse<T> error() {
        return new RestResponse<>(ResultCode.ERROR);
    }

    public static <T> RestResponse<T> error(T data) {
        return new RestResponse<>(ResultCode.ERROR, data);
    }

}
