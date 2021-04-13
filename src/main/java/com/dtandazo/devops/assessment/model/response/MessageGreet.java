package com.dtandazo.devops.assessment.model.response;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MessageGreet {

    @NonNull
    String message;

}
