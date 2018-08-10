package com.mg.utils;

/**
 * @Auther: fujian
 * @Date: 2018/7/11 11:02
 * @Description:
 */
public class MGStringUtil {
    public static String dealXssStr(String str){
        if(str != null){
            str = str.replaceAll("a","A");
        }
        return str;
    }
}
