package com.api.service.reporting.service;

import com.api.service.reporting.configuration.Resources;
import com.api.service.reporting.controller.LoginController;
import com.api.service.reporting.model.LoginRequest;
import com.api.service.reporting.model.TokenResponse;
import com.api.service.reporting.service.impl.LoginServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@WebAppConfiguration
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(LoginController.class)
public class LoginServiceTest {

    @InjectMocks
    public LoginServiceImpl loginServiceImpl;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private Resources resources;

    @Test
    public void shouldNotGetUserToken() {

        LoginRequest loginRequest = LoginRequest.builder().email("a@a.com").build();

        Optional<TokenResponse> tokenResponse = loginServiceImpl.getUserToken(loginRequest);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        Optional.empty();
        map.add("email", loginRequest.getEmail());
        map.add("password", loginRequest.getPassword());

        when(resources.getLoginApiUrl()).thenReturn("strurl");
        when(restTemplate.postForEntity("strurl", map, String.class)).thenThrow(new RuntimeException());

        Optional<TokenResponse> tokenResp = loginServiceImpl.getUserToken(loginRequest);

        assertEquals(tokenResp, Optional.empty());

    }


}
