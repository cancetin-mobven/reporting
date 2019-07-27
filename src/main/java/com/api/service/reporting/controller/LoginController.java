package com.api.service.reporting.controller;

import com.api.service.reporting.exception.InternalErrorException;
import com.api.service.reporting.model.LoginRequest;
import com.api.service.reporting.model.TokenResponse;
import com.api.service.reporting.service.impl.LoginServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/login")
@Slf4j

public class LoginController {

    @Autowired
    LoginServiceImpl loginServiceImpl;

    @RequestMapping(value = "/api", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<TokenResponse> saveAddress(@RequestBody @Valid LoginRequest loginRequest, HttpServletRequest request) {

        return loginServiceImpl.getUserToken(loginRequest)
                .map(authToken -> new ResponseEntity<>(authToken, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity(new InternalErrorException("Internal error during getting access token", HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR));

    }

}
