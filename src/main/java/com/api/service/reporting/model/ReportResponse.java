package com.api.service.reporting.model;

import lombok.*;
import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReportResponse {

    String status;

    ArrayList<ReportResponseData> response;
}
