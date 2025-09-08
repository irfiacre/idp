package com.example.idp.response;

public record UserRegistrationResponse(
        String firstName,
        String lastName,
        String email,
        Integer verification_code
) {}
