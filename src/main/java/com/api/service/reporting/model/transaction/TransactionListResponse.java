package com.api.service.reporting.model.transaction;

import lombok.*;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TransactionListResponse {

    Integer per_page;
    Integer current_page;
    String next_page_url;
    Integer prev_page_url;
    Integer from;
    Integer to;
    ArrayList<TransactionData> data;

}
