package com.api.service.reporting.service.impl;

import com.api.service.reporting.configuration.Resources;
import com.api.service.reporting.model.TransactionQueryRequest;
import com.api.service.reporting.model.transactionQuery.TransactionQueryResponse;
import com.api.service.reporting.service.TransactionQueryService;
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
public class TransactionQueryImpl implements TransactionQueryService {

    private final Logger logger = LoggerFactory.getLogger(TransactionQueryImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Resources resources;

    public Optional<TransactionQueryResponse> getReport(String accessToken, TransactionQueryRequest transactionQueryRequest) {

        Gson gson = new GsonBuilder().serializeNulls().create();
        String reportReq = gson.toJson(transactionQueryRequest);

        HttpHeaders head=createHeaders(accessToken);
        head.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(reportReq, head);

        TransactionQueryResponse reportResponse = null;
        try {

            ResponseEntity<String> response = restTemplate.postForEntity(resources.getTransactionQueryApiUrl(), entity, String.class);
            if(response.getStatusCode()== HttpStatus.OK) {
                System.out.println("ddfdf  " + response.getBody());
                Gson g = new Gson();
                reportResponse = g.fromJson(response.getBody(), TransactionQueryResponse.class);
            } else return Optional.empty();

        }catch (HttpClientErrorException e){ //4XX
            logger.error(e.getMessage());
            return  Optional.empty();
        }catch (HttpServerErrorException ex){ //5XX
            logger.error(ex.getMessage());
            return  Optional.empty();
        }catch (Exception exc){
            logger.error(exc.getMessage());
            return Optional.empty();
        }
        return Optional.of(reportResponse);
    }

    HttpHeaders createHeaders(String accessToken){
        return new HttpHeaders() {{
            set( "Authorization", accessToken );
        }};
    }

}
