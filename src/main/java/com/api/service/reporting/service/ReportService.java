package com.api.service.reporting.service;

import com.api.service.reporting.model.LoginRequest;
import com.api.service.reporting.model.ReportRequest;
import com.api.service.reporting.model.ReportResponse;
import com.api.service.reporting.model.TokenResponse;

import java.util.Optional;

public interface ReportService {

    Optional<ReportResponse> getReport(String accessToken,ReportRequest reportRequest);

}
