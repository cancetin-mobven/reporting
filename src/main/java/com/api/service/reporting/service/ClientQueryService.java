package com.api.service.reporting.service;

import com.api.service.reporting.model.ClientQueryRequest;
import com.api.service.reporting.model.clientQuery.ClientQueryResponse;

import java.util.Optional;

public interface ClientQueryService {
    Optional<ClientQueryResponse> getReport(String accessToken, ClientQueryRequest clientQueryRequest) ;
}
