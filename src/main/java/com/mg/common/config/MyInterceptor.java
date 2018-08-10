package com.mg.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Set;

/**
 * @Auther: fujian
 * @Date: 2018/7/11 09:44
 * @Description:
 */
public class MyInterceptor implements HandlerInterceptor {
    private static Logger log = LoggerFactory.getLogger(MyInterceptor.class);

    private String regx = "";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {

        Map<String,String[]> paramMap = request.getParameterMap();
        Set<String> ketSet = paramMap.keySet();
        for(String key:ketSet){
            System.err.println(paramMap.get(key)[0]);
        }
        log.info("-----------进入请求地址前拦截------------");

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        log.info("------------处理请求完成后视图渲染之前的处理操作------------");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        log.info("-----------视图渲染之后的操作------------");
    }
}
