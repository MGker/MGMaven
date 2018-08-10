package com.mg.controller;

import com.mg.beans.RoleEntity;
import com.mg.beans.Temp;
import com.mg.common.Result;
import com.mg.common.rest.MyException;
import com.mg.dao.Role.RoleRepository;
import com.mg.dao.User.UserRepository;
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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName TestController
 * @Author fujian
 * @Date 2018-06-25 17:26
 **/
@Controller
public class TestController {
    private Logger log = LoggerFactory.getLogger(TestController.class);

    @Resource
    UserRepository userRepsitory;
    @Resource
    RoleRepository roleRepositoty;
    private int num =0;
    @RequestMapping("index")
    public String index(Model model, @RequestParam(required = false) String name, String age){
        System.out.println("参数值："+name);
        model.addAttribute("name","Mgker Station");
        log.info("info访问首页");
        log.warn("warn访问首页");
        log.debug("debug访问首页");
        log.error("error访问首页");
        //HttpStatus.CREATED.value();
        return "index";
    }
@RequestMapping("/addOne")
public ResponseEntity addOne(){
        return new ResponseEntity(num++,HttpStatus.OK);
}
    @RequestMapping("addRole")
    public ResponseEntity addUser(Temp role, RoleEntity role1){
        System.out.println(role);
        System.out.println(role1);
        roleRepositoty.save(role1);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    /*@RequestMapping("allRole")
    public ResponseEntity selectAllRole() throws MyException {
        //throw new Exception();
        List<Role> roles = roleRepositoty.selectAllRoles();
        //return new ResponseEntity(roles,HttpStatus.OK);
        throw new MyException();
    }

    @RequestMapping("disableRole")
    public ResponseEntity updateRole(long id){
        roleRepositoty.disableRoleById(id);
        return new ResponseEntity(HttpStatus.OK);
    }*/
    @RequestMapping("/auth/login")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/auth/doLogin")
    public ResponseEntity doLogin(HttpServletRequest request){
        String userCode = (String)request.getParameter("username");
        String userPwd = (String)request.getParameter("password");
        // shiro认证
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userCode, userPwd);
        Result result = new Result();
        result.setSuccess(true);
        try {
            subject.login(token);
            result.setMessage("登录成功");
        } catch (UnknownAccountException e) {
            log.info("账户不存在");
            result.setMessage("賬戶不存在");
        } catch (DisabledAccountException e) {
            log.info("账户存在问题");result.setMessage("账户存在问题");
        } catch (AuthenticationException e) {
           log.info("密码错误");result.setMessage("密码错误");
        } catch (Exception e) {
            log.info("登陆异常", e);result.setMessage("登陆异常");
        }

        return new ResponseEntity(result,HttpStatus.OK);
    }

    @RequestMapping("/home")
    public String home(){


        return  "home";
    }

    @RequestMapping("/auth/logout")
    public String loginOut(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "/login";
    }

    @RequestMapping("/admin")
    @ResponseBody
    public String admin(){
        Subject subject = SecurityUtils.getSubject();
        boolean hasRole = subject.hasRole("guest");
        return "has admin:"+hasRole;
    }
}
