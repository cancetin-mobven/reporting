package com.api.service.reporting.model;

import com.api.service.reporting.model.transaction.Currency;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReportResponseData {

    int count;
    Long total;
    Currency currency; // ? enum :  String

}
