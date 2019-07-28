package com.api.service.reporting.model.transaction;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class IpnMerchant {

    String transactionId;
    String referenceNo;
    Long amount;
    String currency;
    Long date;
    String code;
    String message;
    String operation;
    String type;
    String status;
    String customData;
    String paymentType;
    String authTransactionId;
    String token;
    Long convertedAmount;
    String convertedCurrency;
    String IPNUrl;
    String ipnType;

}
