package com.dtandazo.devops.assessment.security.config;

import com.dtandazo.devops.assessment.error.ExceptionAuthorizationFilter;
import com.dtandazo.devops.assessment.security.ApiAuthenticationManager;
import com.dtandazo.devops.assessment.security.JwtAuthenticationEntryPoint;
import com.dtandazo.devops.assessment.security.filter.JWTAuthorizationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
@Configuration
public class WebSecurityApiConfig extends WebSecurityConfigurerAdapter {

    Logger logger = LoggerFactory.getLogger(WebSecurityApiConfig.class);

    private final JwtAuthenticationEntryPoint unauthorizedHandler;

    private final ApiAuthenticationManager apiAuthenticationManager;

    private final MessageSource messageSource;

    public WebSecurityApiConfig(JwtAuthenticationEntryPoint unauthorizedHandler, ApiAuthenticationManager apiAuthenticationManager, MessageSource messageSource) {
        this.unauthorizedHandler = unauthorizedHandler;
        this.apiAuthenticationManager = apiAuthenticationManager;
        this.messageSource = messageSource;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        JWTAuthorizationFilter jwtAuthorizationFilter = new JWTAuthorizationFilter(apiAuthenticationManager);
        ExceptionAuthorizationFilter exceptionHandlerFilter = new ExceptionAuthorizationFilter(messageSource);
        httpSecurity.cors()
            .and().
            csrf().
            disable()
            .exceptionHandling()
            .authenticationEntryPoint(unauthorizedHandler)
            .and().authorizeRequests()
            .antMatchers("/auth", "/actuator", "/actuator/health", "/v3/api-docs", "/configuration/ui", "/swagger-resources", "/configuration/security", "/swagger-ui.html", "/webjars/**", "/swagger-resources/configuration/ui", "/swagger-resources/configuration/security").permitAll()
            .antMatchers("/swagger-ui", "/swagger-ui/index.html", "/swagger-ui/swagger-ui-standalone-preset.js", "/swagger-ui/swagger-ui-bundle.js", "/swagger-ui/favicon-32x32.png", "/swagger-ui/favicon-16x16.png", "/v3/api-docs/swagger-config", "/swagger-ui/swagger-ui.css").permitAll()
            .anyRequest().authenticated()
            .and()
            .addFilter(jwtAuthorizationFilter).addFilterBefore(exceptionHandlerFilter, JWTAuthorizationFilter.class)
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        logger.info("Success WebSecurityApiConfig");
    }
}
