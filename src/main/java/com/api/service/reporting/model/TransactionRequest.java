package com.api.service.reporting.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionRequest {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "From Date cannot be null")
    private String fromDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "From Date cannot be null")
    private String toDate;

    private String status;

    private String operation;

    private Integer merchantId;

    private Integer acquirerId;

    private String paymentMethod;

    private String errorCode;

    private String filterField;

    private String filterValue;

    private String page;

}
