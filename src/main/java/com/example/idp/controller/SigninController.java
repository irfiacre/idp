package com.example.idp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SigninController {

    @GetMapping("/login")
    public String customLoginPage() {
        return "signin";
    }
}
