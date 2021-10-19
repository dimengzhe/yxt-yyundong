package com.yxt.yyd.utils;

import java.io.Serializable;

/**
 * @author dimengzhe
 * @date 2020/12/2 10:07
 * @description
 */

public class R<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 成功
     */
    public static final int SUCCESS = Constants.SUCCESS;

    /**
     * 失败
     */
    public static final int FAIL = Constants.FAIL;

    private int code;

    private String msg;
    private boolean success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    private T data;

    public static <T> R<T> ok() {
        return restResult(null, SUCCESS, null, true);
    }

    public static <T> R<T> ok(T data) {
        return restResult(data, SUCCESS, null, true);
    }

    public static <T> R<T> ok(T data, String msg) {
        return restResult(data, SUCCESS, msg, true);
    }

    public static <T> R<T> fail() {
        return restResult(null, FAIL, null, false);
    }

    public static <T> R<T> fail(String msg) {
        return restResult(null, FAIL, msg, false);
    }

    public static <T> R<T> fail(T data) {
        return restResult(data, FAIL, null, false);
    }

    public static <T> R<T> fail(T data, String msg) {
        return restResult(data, FAIL, msg, false);
    }

    public static <T> R<T> fail(int code, String msg) {
        return restResult(null, code, msg, false);
    }

    private static <T> R<T> restResult(T data, int code, String msg, boolean success) {
        R<T> apiResult = new R<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        apiResult.setSuccess(success);
        return apiResult;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
