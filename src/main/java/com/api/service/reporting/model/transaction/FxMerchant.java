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
public class FxMerchant {
      Long originalAmount;
      String originalCurrency;
      Long convertedAmount;
      String convertedCurrency;

}
