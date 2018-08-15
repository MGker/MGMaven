package com.mg.controller.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Auther: fujian
 * @Date: 2018/8/13 17:06
 * @Description: 主页面控制器
 */
@Controller
public class MainController {

    @RequestMapping("main")
    public String home(){
        return "main";
    }
}
