package com.mg.common.config;

import com.mg.common.rest.MyException;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: fujian
 * @Date: 2018/7/12 10:44
 * @Description:
 */
@ControllerAdvice
public class GlobalExceptionHandle {

    @ExceptionHandler(value = MyException.class)
    //@ResponseBody
    public String myErrorHandler(HttpServletRequest req,MyException e){
//        ModelAndView mv = new ModelAndView("defaultError");
//        mv.addObject("e", e);
//        mv.addObject("uri", req.getRequestURI());
        return "error/defaultError";
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public String defaultErrorHandler(Exception e){

        return "error";
    }

}
