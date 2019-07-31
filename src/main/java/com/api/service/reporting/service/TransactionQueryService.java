package com.api.service.reporting.service;

import com.api.service.reporting.model.TransactionQueryRequest;
import com.api.service.reporting.model.transactionQuery.TransactionQueryResponse;

import java.util.Optional;

public interface TransactionQueryService {
    Optional<TransactionQueryResponse> getReport(String accessToken, TransactionQueryRequest transactionQueryRequest);

}



