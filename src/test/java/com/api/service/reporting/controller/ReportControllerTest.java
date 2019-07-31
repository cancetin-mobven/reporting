package com.api.service.reporting.controller;

import com.api.service.reporting.configuration.Resources;
import com.api.service.reporting.model.ReportRequest;
import com.api.service.reporting.model.ReportResponse;
import com.api.service.reporting.service.impl.ReportServiceImpl;
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
@WebMvcTest(ReportController.class)
public class ReportControllerTest {

    @InjectMocks
    private ReportController reportController;

    @Mock
    ReportServiceImpl reportService;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private Resources resources;

    @Test
    public void shouldGetTokenApproved() {
        HttpServletRequest mockedRequest = mock(HttpServletRequest.class);
        ReportRequest reportRequest = new ReportRequest();
        reportRequest.setMerchant(1);
        ReportResponse reportResponse = ReportResponse.builder().status("Approved").build();
        String token = "token";

        when(reportService.getReport(token, reportRequest)).thenReturn(Optional.of(reportResponse));
        ResponseEntity<ReportResponse> reportEntityResponse = reportController.getReport(token, reportRequest, mockedRequest);

        assertEquals(reportEntityResponse.getBody().getStatus(), reportResponse.getStatus());

    }

    @Test
    public void shouldNotGetTokenApproved() {
        HttpServletRequest mockedRequest = mock(HttpServletRequest.class);
        ReportRequest reportRequest = new ReportRequest();
        reportRequest.setMerchant(1);
        ReportResponse reportResponse = ReportResponse.builder().status("Approved").build();
        String token = null;
        when(reportService.getReport(token, reportRequest)).thenReturn(Optional.of(reportResponse));

        ResponseEntity<ReportResponse> reportEntityResponse = reportController.getReport(token, reportRequest, mockedRequest);

        assertEquals(reportEntityResponse.getStatusCode().value(), HttpStatus.FORBIDDEN.value());
    }


}
