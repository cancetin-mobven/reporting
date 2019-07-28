package com.api.service.reporting.model.transaction;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FxMerchant {
      Long originalAmount;
      String originalCurrency;
      Long convertedAmount;
      String convertedCurrency;

}
