package com.api.service.reporting.model.transactionQuery;

import com.api.service.reporting.model.transaction.CustomerInfo;
import com.api.service.reporting.model.transaction.Fx;
import com.api.service.reporting.model.transaction.Merchant;
import com.api.service.reporting.model.transaction.Transaction;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TransactionQueryResponse {

    CustomerInfo customerInfo;
    Fx fx;
    Merchant merchant;
    Transaction transaction;

}
