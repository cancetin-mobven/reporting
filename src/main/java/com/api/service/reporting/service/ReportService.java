package com.api.service.reporting.service;

import com.api.service.reporting.model.ReportRequest;
import com.api.service.reporting.model.ReportResponse;

import java.util.Optional;

public interface ReportService {

    Optional<ReportResponse> getReport(String accessToken, ReportRequest reportRequest);

}
