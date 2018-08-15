package com.mg.controller.index;

import com.mg.beans.UserEntity;
import com.mg.common.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: fujian
 * @Date: 2018/7/31 09:03
 * @Description: 包含 用户 登录,登出,注册 接口
 */
@Controller
@RequestMapping("auth")
public class LoginController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/login")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/doLogin")
    public ResponseEntity doLogin(HttpServletRequest request,UserEntity user){
        String userCode = (String)request.getParameter("username");
        userCode = user.getUserName();
        String userPwd = (String)request.getParameter("password");
        userPwd = user.getPassWord();
        // shiro认证
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userCode, userPwd);
        Result result = new Result();
        result.setSuccess(true);
        try {
            subject.login(token);
            result.setMessage("登录成功");
            result.setCode(1);
        } catch (UnknownAccountException e) {
            log.info("账户不存在");
            result.setCode(0);
            result.setMessage("用户名或密码错误");
        } catch (DisabledAccountException e) {
            log.info("账户被禁用");result.setMessage("该账户被禁用");
            result.setCode(0);
        } catch (AuthenticationException e) {
            log.info("密码错误");
            result.setMessage("用户名或密码错误");
            result.setCode(0);
        } catch (Exception e) {
            log.info("登陆异常,请稍后重试", e);result.setMessage("登陆异常,请稍后重试");
            result.setCode(-1);
        }

        return new ResponseEntity(result,HttpStatus.OK);
    }

    @RequestMapping("/logout")
    public String loginOut(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "/login";
    }

    @RequestMapping("/register")
    public String toRegister(){

        return "register";
    }

    @RequestMapping("doRegister")
    public ResponseEntity doRegister(){


        return new ResponseEntity(HttpStatus.CREATED);
    }


}
