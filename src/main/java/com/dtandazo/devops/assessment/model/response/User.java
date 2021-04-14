package com.dtandazo.devops.assessment.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
public class User {

    String userName;

    String password;

    @NonNull
    String jwtToken;
}
