package com.dtandazo.devops.assessment.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class ApiAuthenticationManager implements AuthenticationManager {

    @Value("${devops.auth.credential.user}")
    private String credentialUser;

    @Value("${devops.auth.credential.password}")
    private String credentialPassword;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (credentialUser.equals(authentication.getPrincipal()) && credentialPassword.equals(authentication.getCredentials())) {
           return authentication;
        }
        throw new BadCredentialsException("User or password Incorrect.");
    }

}
