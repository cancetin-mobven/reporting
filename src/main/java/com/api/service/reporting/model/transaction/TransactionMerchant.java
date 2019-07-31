package com.api.service.reporting.model.transaction;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionMerchant {
    String referenceNo;
    String status;
    String customData;
    String type;
    String operation;
    String created_at;
    String message;
    String transactionId;
    private Long merchantId;
    private String channel;
    private String chainId;
    private Long agentInfoId;
    private String updated_at;
    private Long id;
    private Long fxTransactionId;
    private Long acquirerTransactionId;
    private String code;

}
