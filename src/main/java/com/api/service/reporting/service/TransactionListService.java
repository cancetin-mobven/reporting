package com.api.service.reporting.service;

import com.api.service.reporting.model.TransactionRequest;
import com.api.service.reporting.model.transaction.TransactionListResponse;

import java.util.Optional;

public interface TransactionListService {
    Optional<TransactionListResponse> getReport(String accessToken, TransactionRequest transactionRequest);
}
