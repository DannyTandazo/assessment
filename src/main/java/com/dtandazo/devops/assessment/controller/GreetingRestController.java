package com.dtandazo.devops.assessment.controller;


import com.dtandazo.devops.assessment.model.request.GreetingDevOps;
import com.dtandazo.devops.assessment.model.response.MessageGreet;
import com.dtandazo.devops.assessment.service.GreetingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingRestController {

    final GreetingService greetingService;

    public GreetingRestController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @Operation(operationId = "greet", summary = "Greet Message DevOps")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful Operation, Return informationMessage",
            content = @Content(schema = @Schema(implementation = MessageGreet.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
        @ApiResponse(responseCode = "405", description = "ERROR")})
    @Parameters(value = {
        @Parameter(in = ParameterIn.HEADER, name = "X-Parse-REST-API-Key", description = "Api Key", required = true, content = @Content(schema = @Schema(implementation = String.class))),
        @Parameter(in = ParameterIn.HEADER, name = "X-JWT-KWY", description = "Authorization Bearer", required = true, content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping("/DevOps")
    public MessageGreet greet(@RequestBody GreetingDevOps greetingDevOps) {
        return greetingService.getMessage(greetingDevOps);
    }
}
