package com.lin.htmldemo.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/hello")
public class HelloController {


    @Value("${age}")
    private String age;


    //http://localhost:8080/hello/12
    @RequestMapping(value = "/hello/{name}", method = RequestMethod.GET)
    @ResponseBody
    public String hello(@PathVariable String name) {

        return "hello==" + name + "==" + age;
    }


    //http://localhost:8080/wooo?name=lalala
    @RequestMapping(value = {"world", "wooo"})
    @ResponseBody
    public String world(@RequestParam(required = false, defaultValue = "什么鬼") String name) {
        return name;
    }

    /**
     * 测试hello
     *
     * @return http://localhost:8080/hi
     */
    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    public String hello(Model model) {
        model.addAttribute("name", "Dear");
        return "hello/hello";
    }

    /**
     * 测试hello
     *
     * @return http://localhost:8080/java
     */
    @RequestMapping(value = "/java", method = RequestMethod.GET)
    public String java(Model model) {
        model.addAttribute("name", "Dear");
        return "hello/java";
    }


    //默认首页
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("name", "lin");
        return "hello/index";
    }

    public String cookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie("name", "lin");
        response.addCookie(cookie);
        Cookie[] c = request.getCookies();
        return "";

    }


    @RequestMapping(value = "/validateBefore", method ={RequestMethod.GET,RequestMethod.POST})
    public String validateBefore() {
        return "validate";
    }


    @RequestMapping(value = "/validate", method ={RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String validate(String fname) {
        System.out.println("fname=" + fname);
        return fname;
    }

    @RequestMapping(value ="/getInfo",method ={RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String getInfo(HttpServletRequest request,String name) {
        System.out.println(request.getQueryString());
        Map<String,Object> result=new HashMap<>();
        result.put("name",name);
        result.put("age",21);
        result.put("address","china");
       return JSON.toJSONString(result);
    }

}
