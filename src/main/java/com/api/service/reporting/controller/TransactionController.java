package com.api.service.reporting.controller;


import com.api.service.reporting.exception.InternalErrorException;
import com.api.service.reporting.model.*;
import com.api.service.reporting.model.transaction.TransactionListResponse;
import com.api.service.reporting.service.impl.TransactionListImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/transaction")
@Slf4j
public class TransactionController {

    @Autowired
    TransactionListImpl transactionServiceImpl;

    @RequestMapping(value = "/api", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<TransactionListResponse> getReport(
            @RequestHeader(name = "Authorization", required = false) String accessToken,
            @RequestBody @Valid TransactionRequest transactionRequest, HttpServletRequest request) {

        if(accessToken == null || transactionRequest == null){
            return new ResponseEntity(new InternalErrorException("Not a valid request! ", HttpStatus.FORBIDDEN), HttpStatus.FORBIDDEN);
        }

        System.out.println(accessToken);
        System.out.println(transactionRequest.getToDate());
        System.out.println(transactionRequest.getFromDate());

        return  transactionServiceImpl.getReport(accessToken,transactionRequest)
                .map(report -> new ResponseEntity<>(report, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity(new InternalErrorException("Internal error during getting access token", HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR));


    /*    return  transactionServiceImpl.getReport(accessToken,reportRequest)
                .map(report -> new ResponseEntity<>(report, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity(new InternalErrorException("Internal error during getting access token", HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR));
*/
    }

    @RequestMapping(value = "/apii", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<TransactionListResponse> getReports(
            @RequestHeader(name = "Authorization", required = false) String accessToken,
            @RequestBody @Valid TransactionListResponse transactionListResponse, HttpServletRequest request) {

        return null;

    }
}
