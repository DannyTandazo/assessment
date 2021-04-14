package com.dtandazo.devops.assessment.service;

import com.dtandazo.devops.assessment.model.response.User;

public interface TokenService {

    User getToken(String username, String password);
}
