package com.api.service.reporting.controller;


import com.api.service.reporting.model.LoginRequest;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

import java.util.Optional;

import static org.mockito.Mockito.*;

// @ContextConfiguration(classes = {AppConfig.class})
    @WebAppConfiguration
    @ExtendWith({SpringExtension.class, MockitoExtension.class})
    @RunWith(MockitoJUnitRunner.class)
    @WebMvcTest(LoginController.class)
public class LoginControllerTest {

        @InjectMocks
        private LoginController loginController;

        @Mock
        LoginServiceImpl loginService;
    /*
            @Before
            public void setup() {
                MockitoAnnotations.initMocks(this);
            }
    */
@Autowired
private MockMvc mockMvc;

        @Test
        public void shouldListAccountByClient() {
            HttpServletRequest  mockedRequest = mock(HttpServletRequest.class);
            LoginRequest loginRequest = new LoginRequest();
            loginRequest.setPassword("s");
            loginRequest.setEmail("sdfsd");
            when(loginService.getUserToken(loginRequest)).thenReturn(Optional.empty());
            loginController.saveAddress(loginRequest ,mockedRequest);
            verify(loginService, Mockito.times(1)).getUserToken(loginRequest);
        }

       @Test
       public void shouldListAccountByClients() {
           HttpServletRequest  mockedRequest = mock(HttpServletRequest.class);
           LoginRequest loginRequest = new LoginRequest();
           loginRequest.setPassword(null);
           loginRequest.setEmail("");
           when(loginService.getUserToken(loginRequest)).thenReturn(Optional.empty());
           loginController.saveAddress(loginRequest ,mockedRequest);
           verify(loginService, Mockito.times(1)).getUserToken(loginRequest);
       }

   }
