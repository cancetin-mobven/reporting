package com.api.service.reporting.model.transaction;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Ipn {

    Boolean sent;
    IpnMerchant merchant;




}
