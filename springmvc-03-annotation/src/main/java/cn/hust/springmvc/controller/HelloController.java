package cn.hust.springmvc.controller;

import cn.hust.springmvc.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @Autowired
    HelloService helloService;

    @ResponseBody
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String sayHello() {
        return helloService.sayHello("tomcat...");
    }

    // springMVC 会在指定的路径下找 jsp 文件，解析并返回
    @GetMapping("/suc")
    public String success() {
        return "success";
    }
}
