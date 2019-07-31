package com.api.service.reporting.service.impl;

import com.api.service.reporting.configuration.Resources;
import com.api.service.reporting.model.TokenResponse;
import com.api.service.reporting.model.LoginRequest;
import com.api.service.reporting.service.LoginService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {

    private final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Resources resources;

    ResponseEntity<String> response = new ResponseEntity<>("Error with Login information", HttpStatus.INTERNAL_SERVER_ERROR);

    @Override
    public Optional<TokenResponse> getUserToken(LoginRequest loginRequest) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("email", loginRequest.getEmail());
        map.add("password", loginRequest.getPassword());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        TokenResponse tokenResponse ;
                try {
            response = restTemplate.postForEntity(resources.getLoginApiUrl(), request, String.class);
            Gson g = new Gson();
            tokenResponse = g.fromJson(response.getBody(), TokenResponse.class);

        }catch (Exception e){
            logger.error(e.getMessage());
            return Optional.empty();
        }

        return Optional.of(tokenResponse);
    }

}
