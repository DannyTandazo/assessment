package com.dtandazo.devops.assessment.service.impl;

import com.dtandazo.devops.assessment.model.response.User;
import com.dtandazo.devops.assessment.service.TokenService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.Date;

@Service
public class TokenServiceImpl implements TokenService {

    @Value("${devops.auth.jwt.secretKey}")
    public String secretKey;

    @Value("${devops.auth.jwt.prefix.header.token.authorization}")
    public String prefixHeaderAuthorization;

    @Override
    public User getToken(String username, String password) {

        String jwtToken = Jwts.builder()
            .setSubject(username)
            .setExpiration(new Date(System.currentTimeMillis() + 300000))
            .signWith(SignatureAlgorithm.HS512, secretKey.getBytes(Charset.defaultCharset()))
            .compact();

        return User.builder()
            .userName(username)
            .password(password)
            .jwtToken(prefixHeaderAuthorization.concat(" ").concat(jwtToken)).build();
    }
}
