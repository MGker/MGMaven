package com.mg.common.config;

/**
 * @Auther: fujian
 * @Date: 2018/7/11 10:36
 * @Description:
 */

import com.mg.utils.MGStringUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;

/**自定义过滤器filter*/
public class XssFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) srequest;
        request = new MHttpServletRequest(request);
        System.out.println("进入过滤器,url :"+request.getRequestURI());
        filterChain.doFilter(request, sresponse);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }

}

class MHttpServletRequest extends HttpServletRequestWrapper {

    public MHttpServletRequest(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getParameter(String name) {
        // 返回值之前 先进行过滤
        return MGStringUtil.dealXssStr(super.getParameter(name));
    }

    @Override
    public String[] getParameterValues(String name) {
        // 返回值之前 先进行过滤
        String[] values = super.getParameterValues(name);
        if(values != null){
            for (int i = 0; i < values.length; i++) {
                values[i] = MGStringUtil.dealXssStr(values[i]);
            }
        }
        return values;
    }

}
