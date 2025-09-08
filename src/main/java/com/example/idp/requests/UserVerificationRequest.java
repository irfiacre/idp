package com.example.idp.requests;

public record UserVerificationRequest(
        Integer code,
        String email
) {}
