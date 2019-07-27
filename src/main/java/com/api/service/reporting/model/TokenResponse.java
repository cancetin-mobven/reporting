package com.api.service.reporting.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@JsonIgnoreProperties
@NoArgsConstructor
@Builder
public class TokenResponse {

    String token;

    String status;
}
