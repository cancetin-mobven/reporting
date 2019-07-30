package com.api.service.reporting.controller;

import com.api.service.reporting.exception.InternalErrorException;
import com.api.service.reporting.model.LoginRequest;
import com.api.service.reporting.model.ReportRequest;
import com.api.service.reporting.model.ReportResponse;
import com.api.service.reporting.model.TokenResponse;
import com.api.service.reporting.service.impl.LoginServiceImpl;
import com.api.service.reporting.service.impl.ReportServiceImpl;
import com.api.service.reporting.service.impl.TransactionListImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/report")
@Slf4j
public class ReportController {

    @Autowired
    ReportServiceImpl reportServiceImpL;

    @Autowired
    TransactionListImpl transactionServiceImpl;

    @RequestMapping(value = "/api", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ReportResponse> getReport(
            @RequestHeader(name = "Authorization", required = false) String accessToken,
            @RequestBody @Valid ReportRequest reportRequest, HttpServletRequest request) {

        if(accessToken == null && accessToken.equals("") || reportRequest == null){
           return new ResponseEntity(new InternalErrorException("Not valid request! ", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }


        return  reportServiceImpL.getReport(accessToken,reportRequest)
                .map(report -> new ResponseEntity<>(report, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity(new InternalErrorException("Internal error during getting access token", HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR));


    /*    return  transactionServiceImpl.getReport(accessToken,reportRequest)
                .map(report -> new ResponseEntity<>(report, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity(new InternalErrorException("Internal error during getting access token", HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR));
*/
    }
}
