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
    @RequestMapping("/websocketTest")
    public String toLogin(){
        return "websocket/websocketClient";
    }


}
