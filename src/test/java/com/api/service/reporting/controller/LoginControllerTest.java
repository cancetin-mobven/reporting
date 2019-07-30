package com.api.service.reporting.controller;

import com.api.service.reporting.configuration.Resources;
import com.api.service.reporting.model.LoginRequest;
import com.api.service.reporting.model.TokenResponse;
import com.api.service.reporting.service.LoginService;
import com.api.service.reporting.service.impl.LoginServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.internal.verification.Times;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@WebAppConfiguration
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(LoginController.class)
public class LoginControllerTest {

    @InjectMocks
    private LoginController loginController;

    @Mock
    LoginServiceImpl loginService;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private Resources resources;

    @Test
    public void shouldGetTokenApproved() {
        HttpServletRequest mockedRequest = mock(HttpServletRequest.class);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPassword("password");
        loginRequest.setEmail("email");
        TokenResponse tokenResponse = TokenResponse.builder().status("Approved").token("token").build();

        when(loginService.getUserToken(loginRequest)).thenReturn(Optional.of(tokenResponse));
        ResponseEntity<TokenResponse>  tokenResp = loginController.login(loginRequest, mockedRequest);

        assertEquals(tokenResp.getBody().getStatus(),tokenResponse.getStatus());
        assertEquals(tokenResp.getBody().getToken(),tokenResponse.getToken());

        verify(loginService, Mockito.times(1)).getUserToken(loginRequest);
    }

    @Test
    public void shouldThrowExceptionWhenWrongRequestInfo() {
        HttpServletRequest mockedRequest = mock(HttpServletRequest.class);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPassword("password");
        loginRequest.setEmail("email");

        when(loginService.getUserToken(loginRequest)).thenReturn(Optional.empty());
        ResponseEntity<TokenResponse>  tokenResp = loginController.login(loginRequest, mockedRequest);
        assertEquals(tokenResp.getStatusCode().value(), HttpStatus.INTERNAL_SERVER_ERROR.value());

    }
}
