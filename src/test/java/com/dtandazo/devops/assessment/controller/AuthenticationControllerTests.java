package com.dtandazo.devops.assessment.controller;

import com.dtandazo.devops.assessment.model.response.User;
import com.dtandazo.devops.assessment.security.ApiAuthenticationManager;
import com.dtandazo.devops.assessment.service.TokenService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(properties = {"devops.auth.credential.user=dtandazo",
	"devops.auth.credential.password=601f1889667efaebb33b8c12572835da3f027f78"})
class AuthenticationControllerTests {


	@MockBean
	TokenService tokenService;

	AuthenticateController authenticateController;

	String userName;

	String password;

	@Autowired
	ApiAuthenticationManager apiAuthenticationManager;

	@BeforeAll
	void setUp() {
		userName = "dtandazo";
		password = "601f1889667efaebb33b8c12572835da3f027f78";
		Mockito.doReturn(User.builder().userName(userName).jwtToken("jwtToken").build()).when(tokenService).getToken(userName, password);
	}

	@Test
	void authTest() {
		authenticateController = new AuthenticateController(tokenService, apiAuthenticationManager);
		User user = authenticateController.auth(userName, password);
		Assertions.assertNotNull(user);
		Assertions.assertEquals("dtandazo", user.getUserName());
		Assertions.assertNotNull(user.getJwtToken());
		Assertions.assertEquals("jwtToken", user.getJwtToken());
	}

}
