package com.api.service.reporting.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class Resources {

    @Value("${loginApiUrl}")
    @Setter
    @Getter
    private String loginApiUrl;


    @Value("${reportApiUrl}")
    @Setter
    @Getter
    private String reportApiUrl;



    @Value("${transactionApiUrl}")
    @Setter
    @Getter
    private String transactionApiUrl;

}
