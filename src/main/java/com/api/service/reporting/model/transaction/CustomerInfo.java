package com.api.service.reporting.model.transaction;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CustomerInfo {
      String number;
      String email;
      String billingFirstName;
      String billingLastName;
}
