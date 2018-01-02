package com.blibli.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("")
    public String first(){
        return "home";
    }

    @RequestMapping("/home")
    public String homee(){
        return "home";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/403")
    public String error403() {
        return "error/403";
    }
}
