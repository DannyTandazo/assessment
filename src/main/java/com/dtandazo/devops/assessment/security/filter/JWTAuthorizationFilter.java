package com.dtandazo.devops.assessment.security.filter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private static final String TOKEN_PREFIX = "Bearer ";
    private static final String HEADER_STRING = "X-JWT-KWY";
    private static final String HEADER_REST_API_KEY = "X-Parse-REST-API-Key";
    private static final String SECRET = "SecretKeyToGenJWT";

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        String header = httpServletRequest.getHeader(HEADER_STRING);

        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        UsernamePasswordAuthenticationToken authentication = getAuthentication(httpServletRequest);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) throws ExpiredJwtException {
        String token = request.getHeader(HEADER_STRING);
        String restApiKey = request.getHeader(HEADER_REST_API_KEY);
        if (token != null) {
            String user = Jwts.parser()
                .setSigningKey(SECRET.getBytes(Charset.defaultCharset()))
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody()
                .getSubject();
            if (user != null && isValidApiKey(restApiKey)) {
                return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
            }
            return null;
        }
        return null;
    }

    private boolean isValidApiKey(String restApiKey) {
        return "2f5ae96c-b558-4c7b-a590-a501ae1c3f6c".equals(restApiKey);
    }
}
