package com.api.service.reporting.service;

import com.api.service.reporting.configuration.Resources;
import com.api.service.reporting.model.clientQuery.ClientQueryResponse;
import com.api.service.reporting.service.impl.TransactionQueryImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.web.client.RestTemplate;

import com.api.service.reporting.controller.LoginController;
import com.api.service.reporting.controller.ReportController;
import com.api.service.reporting.model.*;
import com.api.service.reporting.model.transaction.CustomerInfo;
import com.api.service.reporting.model.transaction.Transaction;
import com.api.service.reporting.model.transactionQuery.TransactionQueryResponse;
import com.api.service.reporting.service.impl.*;

import com.api.service.reporting.configuration.Resources;
import com.api.service.reporting.service.LoginService;
import com.api.service.reporting.service.impl.LoginServiceImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;


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
@WebMvcTest(ClientQueryService.class)
public class ClientQueryTest {

    @InjectMocks
    public ClientQueryImpl clientServiceImpl;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private Resources resources;

    @Test
    public void shouldReport() {
        ClientQueryRequest clientQueryRequest = ClientQueryRequest .builder().transactionId("123").build();
        String accessToken = "token";
        when(resources.getClientQueryApiUrl()).thenReturn("url");

        Gson gson = new GsonBuilder().serializeNulls().create();
        String reportReq = gson.toJson(clientQueryRequest);

        HttpHeaders head=createHeaders(accessToken);
        head.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(reportReq, head);

        ClientQueryResponse rp = new ClientQueryResponse();
        rp.setCustomerInfo(CustomerInfo.builder().id(1l).build());

        Gson gson2 = new GsonBuilder().serializeNulls().create();
        String reportReq2 = gson2.toJson(rp);

        when(restTemplate.postForEntity(resources.getClientQueryApiUrl(), entity, String.class)).thenReturn(new ResponseEntity<>(reportReq2, HttpStatus.OK));

        Optional<ClientQueryResponse> reportResp = clientServiceImpl.getReport(accessToken,clientQueryRequest);
        assertEquals(rp.getCustomerInfo().getId(),reportResp.get().getCustomerInfo().getId());
    }


    HttpHeaders createHeaders(String accessToken){
        return new HttpHeaders() {{
            set( "Authorization", accessToken );
        }};
    }
}
