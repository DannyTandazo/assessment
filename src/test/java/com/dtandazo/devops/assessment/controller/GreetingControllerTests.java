package com.dtandazo.devops.assessment.controller;

import com.dtandazo.devops.assessment.model.request.GreetingDevOps;
import com.dtandazo.devops.assessment.model.response.MessageGreet;
import com.dtandazo.devops.assessment.service.GreetingService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GreetingControllerTests {

    @Autowired
    GreetingService greetingService;

    GreetingRestController greetingRestController;


    @Test
    void greetTest() {
        greetingRestController = new GreetingRestController(greetingService);
        GreetingDevOps greetingDevOps = GreetingDevOps.builder()
            .from("Rita Asturia")
            .message("This is a test")
            .to("Juan Perez")
            .timeToLifeSec(45)
            .build();
        MessageGreet greet = greetingRestController.greet(greetingDevOps);
        Assertions.assertNotNull(greet);
        Assertions.assertNotNull(greet.getMessage());
        Assertions.assertEquals("Hello Juan Perez your message will be send", greet.getMessage());
    }
}
