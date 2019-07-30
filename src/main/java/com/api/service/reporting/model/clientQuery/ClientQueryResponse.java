package com.api.service.reporting.model.clientQuery;

import com.api.service.reporting.model.transaction.CustomerInfo;
import lombok.*;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @Builder
    public class ClientQueryResponse {

        CustomerInfo customerInfo;
}
