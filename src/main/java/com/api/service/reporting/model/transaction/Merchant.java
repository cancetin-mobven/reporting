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
public class Merchant {
    Long  id; // ": 1293,
    String name ;//": "Seckin Merchant",
    Boolean  allowPartialRefund;//": true,
    Boolean   allowPartialCapture; //": true
}
