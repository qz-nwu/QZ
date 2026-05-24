package com.qinzhong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/*
 * 访问根路径时打开联调页 index.html
 * */
@Controller
public class StaticPageController {

    @GetMapping("/")
    public String home() {
        return "forward:/index.html";
    }
}
