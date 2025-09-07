package com.example.idp.requests;
import java.util.Set;
public record ClientRegistrationRequest(
   String clientId,
   String clientSecret,
   Set<String> redirectUris,
   Set<String> scopes,
   Set<String> grantTypes
) {}
