package com.api.service.reporting.controller;

import com.api.service.reporting.configuration.Resources;
import com.api.service.reporting.model.ClientQueryRequest;
import com.api.service.reporting.model.clientQuery.ClientQueryResponse;
import com.api.service.reporting.model.transaction.CustomerInfo;
import com.api.service.reporting.service.impl.ClientQueryImpl;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@WebAppConfiguration
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(ClientQueryController.class)
public class ClientQueryControllerTest {

    @InjectMocks
    private ClientQueryController clientQueryController;

    @Mock
    ClientQueryImpl clientQueryImpl;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private Resources resources;

    @Test
    public void shouldGetClientQuery() {
        HttpServletRequest mockedRequest = mock(HttpServletRequest.class);
        ClientQueryRequest clientQueryRequest = new ClientQueryRequest();
        clientQueryRequest.setTransactionId("1");

        ClientQueryResponse clientQueryResponse = ClientQueryResponse.builder().customerInfo(CustomerInfo.builder().shippingFirstName("Can").build()).build();
        String token = "token";

        when(clientQueryImpl.getReport(token, clientQueryRequest)).thenReturn(Optional.of(clientQueryResponse));

        ResponseEntity<ClientQueryResponse> clientEntityResponse =
                clientQueryController.getReport(token, clientQueryRequest, mockedRequest);

        assertEquals(clientEntityResponse.getBody().getCustomerInfo().getShippingFirstName(), clientEntityResponse.getBody().getCustomerInfo().getShippingFirstName());

    }

}

