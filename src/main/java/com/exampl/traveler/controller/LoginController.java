package com.exampl.traveler.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public String login(){
        return "/login/login";
    }

    @RequestMapping("/binLogin")
    public String binLogin(){
        return "/login/binLogin";
    }

    @RequestMapping("/signUp")
    public String signUp(){
        return "/login/signUp";
    }

    @RequestMapping("/binSignUp")
    public String binSignUp(){
        return "/login/binSignUp";
    }

}
