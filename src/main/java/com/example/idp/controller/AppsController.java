package com.example.idp.controller;

import com.example.idp.requests.UserRegistrationRequest;
import com.example.idp.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppsController {
    private final UserService userService;

    public AppsController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/apps")
    public String customLoginPage(Model model) {
        model.addAttribute("message", "Please log in");
        return "apps";
    }

//    @PostMapping("/signup")
//    public String signupPage(UserRegistrationRequest request, Model model) {
//        try {
//            userService.createUser(request);
//            model.addAttribute("successMessage", "Account created successfully! Please log in.");
//            return "signup";
//        } catch (Exception ex) {
//            System.out.println(ex);
//            model.addAttribute("errorMessage", "Something went wrong. Please try again!");
//            return "signup";
//        }
//    }
}
