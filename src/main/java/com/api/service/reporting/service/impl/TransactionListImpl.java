package com.api.service.reporting.service.impl;

import com.api.service.reporting.configuration.Resources;
import com.api.service.reporting.model.TransactionRequest;
import com.api.service.reporting.model.transaction.TransactionListResponse;
import com.api.service.reporting.service.TransactionListService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class TransactionListImpl implements TransactionListService {

    private final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Resources resources;

    public Optional<TransactionListResponse> getReport(String accessToken, TransactionRequest transactionRequest) {

        Gson gson = new GsonBuilder().serializeNulls().create();
        String reportReq = gson.toJson(transactionRequest);

        HttpHeaders head = createHeaders(accessToken);
        head.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(reportReq, head);

        TransactionListResponse reportResponse = null;
        try {

            ResponseEntity<String> response = restTemplate.postForEntity(resources.getTransactionApiUrl(), entity, String.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                Gson g = new Gson();
                reportResponse = g.fromJson(response.getBody(), TransactionListResponse.class);
            } else return Optional.empty();

        } catch (HttpClientErrorException e) { //4XX
            logger.error(e.getMessage());
            return Optional.of(TransactionListResponse.builder().build());
        } catch (HttpServerErrorException ex) { //5XX
            logger.error(ex.getMessage());
            return Optional.of(TransactionListResponse.builder().build());
        } catch (Exception exc) {
            logger.error(exc.getMessage());
            return Optional.empty();
        }
        return Optional.of(reportResponse);
    }

    HttpHeaders createHeaders(String accessToken) {
        return new HttpHeaders() {{
            set("Authorization", accessToken);
        }};
    }

}
