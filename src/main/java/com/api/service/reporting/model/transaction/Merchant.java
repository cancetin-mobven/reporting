package com.api.service.reporting.model.transaction;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Merchant {
    Long  id; // ": 1293,
    String name ;//": "Seckin Merchant",
    Boolean  allowPartialRefund;//": true,
    Boolean   allowPartialCapture; //": true
}
