package com.dtandazo.devops.assessment.service;

import com.dtandazo.devops.assessment.model.response.User;
import com.dtandazo.devops.assessment.service.impl.TokenServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;


@ExtendWith(SpringExtension.class)
@TestPropertySource(properties = {"devops.auth.jwt.secretKey=SecretKeyToGenJWT",
    "devops.auth.jwt.prefix.header.token.authorization=Bearer"})
class TokenServiceTests {

    TokenService tokenService;

    @Value("${devops.auth.jwt.secretKey}")
    public String secretKey;

    @Value("${devops.auth.jwt.prefix.header.token.authorization}")
    public String prefixHeaderAuthorization;

    @Test
    void getTokenTest() {
        tokenService = new TokenServiceImpl();
        ReflectionTestUtils.setField(tokenService, "secretKey", secretKey);
        ReflectionTestUtils.setField(tokenService, "prefixHeaderAuthorization", prefixHeaderAuthorization);

        User user = tokenService.getToken("dtandazo", "601f1889667efaebb33b8c12572835da3f027f78");
        Assertions.assertNotNull(user);
        assertThat(user.getJwtToken(), containsString("Bearer"));
    }
}
