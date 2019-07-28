package com.api.service.reporting.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReportResponse {
 //   ddfdf  {"status":"APPROVED","response":[{"count":10,"total":350961,"currency":"TRY"},{"count":7,"total":9600,"currency":"RUB"},{"count":6,"total":4386,"currency":"EUR"},{"count":2,"total":3640000000,"currency":"IRR"},{"count":1,"total":100,"currency":"CNY"}]}

    String status;

    ArrayList<ReportResponseData> response;
}
