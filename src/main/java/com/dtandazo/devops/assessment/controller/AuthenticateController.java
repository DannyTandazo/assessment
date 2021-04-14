package com.dtandazo.devops.assessment.controller;

import com.dtandazo.devops.assessment.model.response.User;
import com.dtandazo.devops.assessment.security.ApiAuthenticationManager;
import com.dtandazo.devops.assessment.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Authentication", description = "Authentication API for get Authorization Header")
@RestController
public class AuthenticateController {

    final TokenService tokenService;

    final ApiAuthenticationManager apiAuthenticationManager;

    public AuthenticateController(TokenService tokenService, ApiAuthenticationManager apiAuthenticationManager) {
        this.tokenService = tokenService;
        this.apiAuthenticationManager = apiAuthenticationManager;
    }

    @PostMapping("auth")
    @Operation(operationId = "auth", summary = "Authorization with username and password")
    @Parameters(value = {
        @Parameter(name = "user", description = "User authorization", required = true, content = @Content(schema = @Schema(implementation = String.class))),
        @Parameter(name = "password", description = "Password authorization", required = true, content = @Content(schema = @Schema(implementation = String.class)))
    })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful Operation, User Information with Authorization",
            content = @Content(schema = @Schema(implementation = User.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
        @ApiResponse(responseCode = "405", description = "ERROR")})
    public User auth(@RequestParam("user") String username, @RequestParam("password") String password) {
        final Authentication authentication = apiAuthenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return tokenService.getToken(username, password);
    }
}
