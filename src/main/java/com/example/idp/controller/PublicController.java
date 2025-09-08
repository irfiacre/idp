package com.example.idp.controller;

import com.example.idp.entity.User;
import com.example.idp.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/public")
public class PublicController {

    private final UserService userService;

    @GetMapping("/user")
    public ResponseEntity<?> getUser(@RequestParam String email) {
        User user = userService.getUserByEmail(email);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
