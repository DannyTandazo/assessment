package com.dtandazo.devops.assessment.service.impl;

import com.dtandazo.devops.assessment.model.request.GreetingDevOps;
import com.dtandazo.devops.assessment.model.response.MessageGreet;
import com.dtandazo.devops.assessment.service.GreetingService;
import org.springframework.stereotype.Service;

@Service
public class GreetingServiceImpl implements GreetingService {
    @Override
    public MessageGreet getMessage(GreetingDevOps greetingDevOps) {
        String greeting  = String.format("Hello %s your message will be send", greetingDevOps.getTo());
        return MessageGreet.builder().message(greeting).build();
    }
}
