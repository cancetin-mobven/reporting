package com.api.service.reporting.model;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionQueryRequest {

    private String transactionId;

}
