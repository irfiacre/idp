package com.example.idp.response;

public record UserProfileResponse(
        String firstName,
        String lastName,
        String email
) {}
