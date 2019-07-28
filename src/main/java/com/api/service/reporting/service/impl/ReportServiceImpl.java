package com.api.service.reporting.service.impl;

import com.api.service.reporting.configuration.Resources;
import com.api.service.reporting.model.LoginRequest;
import com.api.service.reporting.model.ReportRequest;
import com.api.service.reporting.model.ReportResponse;
import com.api.service.reporting.model.TokenResponse;
import com.api.service.reporting.service.LoginService;
import com.api.service.reporting.service.ReportService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class ReportServiceImpl implements ReportService {

    private final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Resources resources;

    ResponseEntity<String> response = new ResponseEntity<>("Error with Login information", HttpStatus.INTERNAL_SERVER_ERROR);

    public Optional<ReportResponse> getReport(String accessToken, ReportRequest reportRequest) {

        Gson gson = new GsonBuilder().serializeNulls().create();
        String reportReq = gson.toJson(reportRequest);

        System.out.println("asddssssssss    "+reportReq);
        HttpHeaders head=createHeaders(accessToken);
        head.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(reportReq, head);

        System.out.println("bbbbb");

        ReportResponse reportResponse = null;
        try {System.out.println("bbbbbcccc");

            ResponseEntity<String> response = restTemplate.postForEntity(resources.getReportApiUrl(), entity, String.class);
            System.out.println("sssss");
            System.out.println(response);
            if(response.getStatusCode()==HttpStatus.OK) {
                System.out.println("ddfdf  " + response.getBody());
                Gson g = new Gson();
                reportResponse = g.fromJson(response.getBody(), ReportResponse.class);
            } else return Optional.empty();

        }catch (HttpClientErrorException e){ //4XX
            logger.error(e.getMessage());
            return Optional.of( ReportResponse.builder().status(e.getMessage().toString()).build());
        }catch (HttpServerErrorException ex){ //5XX
            logger.error(ex.getMessage());
            return Optional.of( ReportResponse.builder().status(ex.getMessage().toString()).build());
        }catch (Exception exc){
            logger.error(exc.getMessage());
            return Optional.empty();
        }
/*
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Authorization", accessToken);
        headers.add("Content-Type", "application/json");

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        HttpEntity<ReportRequest> request = new HttpEntity<ReportRequest>(reportRequest, headers);

        String reportResponses = restTemplate.postForObject(resources.getReportApiUrl(), request, String.class);
        System.out.println(reportResponses);
*/



     /*
        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("email", loginRequest.getEmail());
        map.add("password", loginRequest.getPassword())
     */

        return Optional.of(reportResponse);
    }

    HttpHeaders createHeaders(String accessToken){
        return new HttpHeaders() {{
            set( "Authorization", accessToken );
        }};
    }

}
