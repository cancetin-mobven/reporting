package com.api.service.reporting.model.transaction;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Transaction {
    TransactionMerchant merchant;
}
