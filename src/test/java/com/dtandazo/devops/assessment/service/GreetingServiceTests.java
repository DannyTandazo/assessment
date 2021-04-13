package com.dtandazo.devops.assessment.service;

import com.dtandazo.devops.assessment.model.request.GreetingDevOps;
import com.dtandazo.devops.assessment.model.response.MessageGreet;
import com.dtandazo.devops.assessment.service.impl.GreetingServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class GreetingServiceTests {

    GreetingService greetingService;

    @Test
    void getMessageTest() {
        greetingService = new GreetingServiceImpl();
        String to = "Danny Tandazo";
        MessageGreet messageGreet = greetingService
            .getMessage(GreetingDevOps.builder().to(to).build());
        Assertions.assertNotNull(messageGreet);
        Assertions.assertEquals("Hello Danny Tandazo your message will be send", messageGreet.getMessage());
    }
}

