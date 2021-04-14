package com.dtandazo.devops.assessment.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "greetingDevOps", description = "Greetings DevOps Message")
public class GreetingDevOps {

    @ApiModelProperty(notes = "message to send", required = true, example = "This is a test")
    String message;

    @ApiModelProperty(notes = "to message", required = true, example = "Juan Perez")
    String to;

    @ApiModelProperty(notes = "from message", required = true, example = "Rita Asturia")
    String from;

    @ApiModelProperty(notes = "timeToLife in Secs", required = true, example = "45")
    int timeToLifeSec;
}
