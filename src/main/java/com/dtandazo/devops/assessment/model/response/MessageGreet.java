package com.dtandazo.devops.assessment.model.response;

import io.swagger.annotations.ApiModel;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "messageGreet", description = "Message Greet Response.")
public class MessageGreet {

    @NonNull
    String message;

}
