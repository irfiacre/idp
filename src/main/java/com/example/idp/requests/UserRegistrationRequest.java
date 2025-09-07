package com.example.idp.requests;

public record UserRegistrationRequest(
        String firstName,
        String lastName,
        String password,
        String email
) {}
