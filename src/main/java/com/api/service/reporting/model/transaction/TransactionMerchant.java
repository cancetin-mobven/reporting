package com.api.service.reporting.model.transaction;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TransactionMerchant {
            String referenceNo; //": "trn-test-seck-1",
    String         status ;//": "ERROR",
             String        customData; //": null,
                    String type ;//": "AUTH",
    String  operation; //"operation": "DIRECT",

                   String created_at; // "created_at": "2018-10-12 15:12:24",
                   String message ;// "message": "Invalid Merchant",
                    String transactionId ;//": "1011028-1539357144-1293"




}
