package com.mg.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName TestController
 * @Author fujian
 * @Date 2018-06-25 17:26
 **/
@Controller
public class TestController {
    private Logger log = LoggerFactory.getLogger(TestController.class);
    @RequestMapping("index")
    public String index(Model model){
        model.addAttribute("name","Mgker Station");
        log.info("info访问首页");
        log.warn("warn访问首页");
        log.debug("debug访问首页");
        log.error("error访问首页");
        //HttpStatus.CREATED.value();
        return "index";
    }
}
