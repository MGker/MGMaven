package com.mg.common.rest;

/**
 * @Auther: fujian
 * @Date: 2018/7/13 09:09
 * @Description:
 */
public class MyException extends RuntimeException{
    @Override
    public String toString() {
        return "my exception"+super.toString();
    }
}
