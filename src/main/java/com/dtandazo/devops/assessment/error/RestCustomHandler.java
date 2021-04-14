package com.dtandazo.devops.assessment.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Locale;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestCustomHandler extends ResponseEntityExceptionHandler {

    private static final String ERROR_METHOD_NOT_ALLOWED = "error.not.allowed.http.method";

    private final MessageSource messageSource;

    public RestCustomHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    Logger customLogger = LoggerFactory.getLogger(RestCustomHandler.class);

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Locale locale = LocaleContextHolder.getLocale();
        String errorMessage = messageSource.getMessage(ERROR_METHOD_NOT_ALLOWED, null, locale);
        customLogger.info("Override HttpStatus.METHOD_NOT_ALLOWED Message {}", errorMessage);
        return new ResponseEntity<>(errorMessage, HttpStatus.METHOD_NOT_ALLOWED);
    }



}
