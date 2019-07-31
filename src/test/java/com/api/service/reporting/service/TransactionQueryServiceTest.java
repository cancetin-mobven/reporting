package com.api.service.reporting.service;

import com.api.service.reporting.configuration.Resources;
import com.api.service.reporting.model.TransactionQueryRequest;
import com.api.service.reporting.model.transaction.CustomerInfo;
import com.api.service.reporting.model.transactionQuery.TransactionQueryResponse;
import com.api.service.reporting.service.impl.TransactionQueryImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@WebAppConfiguration
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(TransactionQueryService.class)
public class TransactionQueryServiceTest {

    @InjectMocks
    public TransactionQueryImpl transactionServiceImpl;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private Resources resources;

    @Test
    public void shouldReport() {
        TransactionQueryRequest transactionQueryRequest = TransactionQueryRequest.builder().transactionId("123").build();
        String accessToken = "token";
        when(resources.getTransactionQueryApiUrl()).thenReturn("url");

        Gson gson = new GsonBuilder().serializeNulls().create();
        String reportReq = gson.toJson(transactionQueryRequest);

        HttpHeaders head = createHeaders(accessToken);
        head.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(reportReq, head);

        TransactionQueryResponse rp = new TransactionQueryResponse();
        rp.setCustomerInfo(CustomerInfo.builder().id(1l).build());

        Gson gson2 = new GsonBuilder().serializeNulls().create();
        String reportReq2 = gson2.toJson(rp);

        when(restTemplate.postForEntity(resources.getTransactionQueryApiUrl(), entity, String.class)).thenReturn(new ResponseEntity<>(reportReq2, HttpStatus.OK));

        Optional<TransactionQueryResponse> reportResp = transactionServiceImpl.getReport(accessToken, transactionQueryRequest);
        assertEquals(rp.getCustomerInfo().getId(), reportResp.get().getCustomerInfo().getId());
    }

    HttpHeaders createHeaders(String accessToken) {
        return new HttpHeaders() {{
            set("Authorization", accessToken);
        }};
    }

}
