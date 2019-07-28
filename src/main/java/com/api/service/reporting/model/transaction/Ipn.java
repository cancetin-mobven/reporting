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

  /*
  "sent": true,
          "merchant": {
        "transactionId": "1010977-1539270521-1293",
                "referenceNo": "trn-test-seck-1",
                "amount": 1600,
                "currency": "RUB",
                "date": 1539270521,
                "code": "00",
                "message": "Approved",
                "operation": "DIRECT",
                "type": "AUTH",
                "status": "APPROVED",
                "customData": null,
                "chainId": "5bbf6779ae569",
                "paymentType": "PAYTOCARD",
                "authTransactionId": "1010977-1539270521-1293",
                "token": "000e1a1294664523f1a6a60c0bf7d59c013fa91fe7103926d9c46d228a2c7322",
                "convertedAmount": 1600,
                "convertedCurrency": "RUB",
                "IPNUrl": "https://requestb.in/10bmd651",
                "ipnType": "MERCHANTIPN"
                */


}
