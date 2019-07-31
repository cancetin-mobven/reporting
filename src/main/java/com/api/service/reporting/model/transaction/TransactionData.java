package com.api.service.reporting.model.transaction;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TransactionData {

    CustomerInfo customerInfo;

    String updated_at;
    String created_at;
    
    Boolean refundable;

    Fx fx;

    Acquirer acquirer;
    Transaction transaction;
    Merchant merchant;
    Ipn ipn;
}
