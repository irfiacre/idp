package com.example.idp.controller;

import com.example.idp.entity.User;
import com.example.idp.requests.UserVerificationRequest;
import com.example.idp.service.key.KeyService;
import lombok.RequiredArgsConstructor;
import com.example.idp.requests.ClientRegistrationRequest;
import com.example.idp.requests.UserRegistrationRequest;
import com.example.idp.service.client.ClientService;
import com.example.idp.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/registration")
public class RegistrationController {
    private final UserService userService;
    private final ClientService clientService;
    private final KeyService keyService;

    @PostMapping("/user")
    public ResponseEntity<?> addUsers(@RequestBody UserRegistrationRequest request) {
        User user = userService.createUser(request);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/client")
    public ResponseEntity<Void> registerClient(@RequestBody ClientRegistrationRequest request) {
        clientService.registerClient(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/verify_user")
    public ResponseEntity<?> verifyUser(@RequestBody UserVerificationRequest request) {
        User user = userService.verifyUser(request);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
