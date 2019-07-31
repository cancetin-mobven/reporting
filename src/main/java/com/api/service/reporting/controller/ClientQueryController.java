package com.api.service.reporting.controller;

import com.api.service.reporting.exception.InternalErrorException;
import com.api.service.reporting.model.ClientQueryRequest;
import com.api.service.reporting.model.clientQuery.ClientQueryResponse;
import com.api.service.reporting.service.impl.ClientQueryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/clientQuery")
@Slf4j
public class ClientQueryController {

    @Autowired
    ClientQueryImpl clientQueryImpl;

    @RequestMapping(value = "/api", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ClientQueryResponse> getReport(
            @RequestHeader(name = "Authorization", required = false) String accessToken,
            @RequestBody @Valid ClientQueryRequest clientRequest, HttpServletRequest request) {

        return clientQueryImpl.getReport(accessToken, clientRequest)
                .map(report -> new ResponseEntity<>(report, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity(new InternalErrorException("Internal error during getting access token", HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR));

    }
}
