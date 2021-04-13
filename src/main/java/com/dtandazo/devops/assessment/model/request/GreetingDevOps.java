package com.dtandazo.devops.assessment.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GreetingDevOps {

    String message;

    String to;

    String from;

    int timeToLifeSec;
}
