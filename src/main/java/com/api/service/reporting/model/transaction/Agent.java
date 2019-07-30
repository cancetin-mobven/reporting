package com.api.service.reporting.model.transaction;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Agent {

    private Long id;
    private String customerIp;
    private String customerUserAgent;
    private String merchantIp;
    private String merchantUserAgent;
    private String created_at;
    private String updated_at;
    private String deleted_at = null;


}
