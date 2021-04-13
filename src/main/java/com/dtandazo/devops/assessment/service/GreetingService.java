package com.dtandazo.devops.assessment.service;

import com.dtandazo.devops.assessment.model.request.GreetingDevOps;
import com.dtandazo.devops.assessment.model.response.MessageGreet;

public interface GreetingService {

    MessageGreet getMessage(GreetingDevOps greetingDevOps);
}
