package com.dtandazo.devops.assessment.controller;


import com.dtandazo.devops.assessment.model.request.GreetingDevOps;
import com.dtandazo.devops.assessment.model.response.MessageGreet;
import com.dtandazo.devops.assessment.service.GreetingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingRestController {

    final GreetingService greetingService;

    public GreetingRestController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @PostMapping("/DevOps")
    public MessageGreet greet(@RequestBody GreetingDevOps greetingDevOps) {
        return greetingService.getMessage(greetingDevOps);
    }
}
