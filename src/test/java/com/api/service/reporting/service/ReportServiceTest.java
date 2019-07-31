package com.api.service.reporting.service;

import com.api.service.reporting.controller.LoginController;
import com.api.service.reporting.controller.ReportController;
import com.api.service.reporting.model.*;
import com.api.service.reporting.service.impl.LoginServiceImpl;

import com.api.service.reporting.configuration.Resources;
import com.api.service.reporting.service.LoginService;
import com.api.service.reporting.service.impl.LoginServiceImpl;
import com.api.service.reporting.service.impl.ReportServiceImpl;
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
@WebMvcTest(ReportController.class)
public class ReportServiceTest {

    @InjectMocks
    public ReportServiceImpl reportServiceImpl;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private Resources resources;


    @Test
    public void shouldReport() {
        ReportRequest reportRequest = ReportRequest.builder().fromDate("2015-07-01").toDate("2015-07-01").build();
        String accessToken = "token";
        when(resources.getReportApiUrl()).thenReturn("url");

        Gson gson = new GsonBuilder().serializeNulls().create();
        String reportReq = gson.toJson(reportRequest);

        HttpHeaders head=createHeaders(accessToken);
        head.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(reportReq, head);
        ReportResponse rp = new ReportResponse();
        rp.setStatus("Approved");

        Gson gson2 = new GsonBuilder().serializeNulls().create();
        String reportReq2 = gson2.toJson(rp);

        when(restTemplate.postForEntity(resources.getReportApiUrl(), entity, String.class)).thenReturn(new ResponseEntity<>(reportReq2, HttpStatus.OK));

        Optional<ReportResponse> reportResp = reportServiceImpl.getReport(accessToken,reportRequest);
        assertEquals(rp.getStatus(),reportResp.get().getStatus());
    }

    @Test
    public void shouldNotGetReport() {
        ReportRequest reportRequest = ReportRequest.builder().fromDate("2015-07-01").toDate("2015-07-01").build();
        String accessToken = "token";
        when(resources.getReportApiUrl()).thenReturn("url");

        Gson gson = new GsonBuilder().serializeNulls().create();
        String reportReq = gson.toJson(reportRequest);

        HttpHeaders head=createHeaders(accessToken);
        head.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(reportReq, head);
        ReportResponse rp = new ReportResponse();
        rp.setStatus("Approved");

        Gson gson2 = new GsonBuilder().serializeNulls().create();
        String reportReq2 = gson2.toJson(rp);

        when(restTemplate.postForEntity(resources.getReportApiUrl(), entity, String.class)).thenThrow(new RuntimeException());

        Optional<ReportResponse> reportResp = reportServiceImpl.getReport(accessToken,reportRequest);
        assertEquals(reportResp,Optional.empty());

    }


    HttpHeaders createHeaders(String accessToken){
        return new HttpHeaders() {{
            set( "Authorization", accessToken );
        }};
    }

}
