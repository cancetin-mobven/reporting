package com.api.service.reporting.controller;


import com.api.service.reporting.configuration.Resources;
import com.api.service.reporting.model.TransactionQueryRequest;
import com.api.service.reporting.model.transaction.Merchant;
import com.api.service.reporting.model.transactionQuery.TransactionQueryResponse;
import com.api.service.reporting.service.impl.TransactionQueryImpl;
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
@WebMvcTest(TransactionQueryController.class)
public class TransactionQueryControllerTest {

    @InjectMocks
    private TransactionQueryController transactionQueryController;

    @Mock
    TransactionQueryImpl transactionQueryImpl;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private Resources resources;

    @Test
    public void shouldGetTransactionQuery() {
        HttpServletRequest mockedRequest = mock(HttpServletRequest.class);
        TransactionQueryRequest transactionQueryRequest = new TransactionQueryRequest();
        transactionQueryRequest.setTransactionId("1");

        TransactionQueryResponse transactionQueryResponse = TransactionQueryResponse.builder().merchant(Merchant.builder().name("Can").build()).build();
        String token = "token";

        when(transactionQueryImpl.getReport(token, transactionQueryRequest)).thenReturn(Optional.of(transactionQueryResponse));

        ResponseEntity<TransactionQueryResponse> transactionEntityResponse =
                transactionQueryController.getReport(token, transactionQueryRequest, mockedRequest);

        assertEquals(transactionEntityResponse.getBody().getMerchant().getName(), transactionEntityResponse.getBody().getMerchant().getName());

    }

}
