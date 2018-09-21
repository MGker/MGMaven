package com.mg.common;

/**
 * @Auther: fujian
 * @Date: 2018/9/3 10:13
 * @Description: 自定义的错误类
 */
public class SomeException extends RuntimeException {
    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public SomeException(String message, int code) {
        super(message);
        this.code = code;
    }

    public SomeException(String message) {
        super(message);
    }
}
