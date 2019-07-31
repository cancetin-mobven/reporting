package com.api.service.reporting.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;


import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ReportRequest{

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "From Date cannot be null")
    private String fromDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "From Date cannot be null")
    private String toDate;

    private Integer acquirer;

    private Integer merchant;
}
