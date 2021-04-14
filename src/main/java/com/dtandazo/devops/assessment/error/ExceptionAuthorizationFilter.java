package com.dtandazo.devops.assessment.error;

import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

public class ExceptionAuthorizationFilter extends OncePerRequestFilter {

    private final MessageSource messageSource;

    public ExceptionAuthorizationFilter(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    private static final String JWT_EXPIRED = "error.jwtExpired";

    private static final String GENERIC_ERROR = "error.generic";

    Logger customLogger = LoggerFactory.getLogger(ExceptionAuthorizationFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } catch (RuntimeException e) {
            customLogger.error(e.getLocalizedMessage(), e);
            Locale locale = LocaleContextHolder.getLocale();
            String errorMessage = "";
            if (e instanceof ExpiredJwtException) {
                errorMessage = messageSource.getMessage(JWT_EXPIRED, null, locale);
                httpServletResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            } else {
                errorMessage = messageSource.getMessage(GENERIC_ERROR, null, locale);
                httpServletResponse.setStatus(INTERNAL_SERVER_ERROR.value());
            }

            httpServletResponse.getWriter().write(errorMessage);
        }
    }
}
