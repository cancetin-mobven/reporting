package com.api.service.reporting.controller;

import com.api.service.reporting.configuration.Resources;
import com.api.service.reporting.model.TransactionRequest;
import com.api.service.reporting.model.transaction.TransactionListResponse;
import com.api.service.reporting.service.impl.TransactionListImpl;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
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
@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {

    @Mock
    TransactionListImpl transactionListImpl;
    @InjectMocks
    private TransactionController transactionController;
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private Resources resources;

    @Test
    public void shouldGetTokenApproved() {
        HttpServletRequest mockedRequest = mock(HttpServletRequest.class);
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setAcquirerId(1);
        TransactionListResponse transactionListResponse = TransactionListResponse.builder().from(1).build();
        String token = "token";

        when(transactionListImpl.getReport(token, transactionRequest)).thenReturn(Optional.of(transactionListResponse));
        ResponseEntity<TransactionListResponse> transactionEntityResponse = transactionController.getReport(token, transactionRequest, mockedRequest);

        assertEquals(transactionEntityResponse.getBody().getFrom(), transactionListResponse.getFrom());

    }

    @Test
    public void shouldNotGetTokenApproved() {
        HttpServletRequest mockedRequest = mock(HttpServletRequest.class);
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setAcquirerId(1);
        TransactionListResponse transactionListResponse = TransactionListResponse.builder().from(1).build();
        String token = null;
        when(transactionListImpl.getReport(token, transactionRequest)).thenReturn(Optional.of(transactionListResponse));

        ResponseEntity<TransactionListResponse> transactionEntityResponse = transactionController.getReport(token, transactionRequest, mockedRequest);

        assertEquals(transactionEntityResponse.getStatusCode().value(), HttpStatus.FORBIDDEN.value());
    }
}

