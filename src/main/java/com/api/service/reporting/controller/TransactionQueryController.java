package com.api.service.reporting.controller;

import com.api.service.reporting.exception.InternalErrorException;
import com.api.service.reporting.model.TransactionQueryRequest;
import com.api.service.reporting.model.transactionQuery.TransactionQueryResponse;
import com.api.service.reporting.service.impl.TransactionQueryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/transactionQuery")
@Slf4j
public class TransactionQueryController {

    @Autowired
    TransactionQueryImpl transactionQueryImpl;

    @RequestMapping(value = "/api", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<TransactionQueryResponse> getReport(
            @RequestHeader(name = "Authorization", required = false) String accessToken,
            @RequestBody @Valid TransactionQueryRequest transactionRequest, HttpServletRequest request) {

        return transactionQueryImpl.getReport(accessToken, transactionRequest)
                .map(report -> new ResponseEntity<>(report, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity(new InternalErrorException("Internal error during getting access token", HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR));

    }
}
