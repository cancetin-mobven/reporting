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

    /*{
    "per_page": 50,
    "current_page": 1,
    "next_page_url": "https://sandbox-reporting.rpdpymnt.com/api/v3/transaction/list?page=2",
    "prev_page_url": null,
    "from": 1,
    "to": 50,
    "data": [
        {
            "customerInfo": {
                "number": "411111XXXXXX1111",
                "email": "seckin@bumin.io",
                "billingFirstName": "SECKIN",
                "billingLastName": "SEN"
            },
            "updated_at": "2018-10-12 15:12:25",
            "created_at": "2018-10-12 15:12:24",
            "fx": {
                "merchant": {
                    "originalAmount": 1500,
                    "originalCurrency": "RUB",
                    "convertedAmount": 1500,
                    "convertedCurrency": "RUB"
                }
            },
            "acquirer": {
                "id": 115,
                "name": "RoyalPay PayToCard",
                "code": "RP",
                "type": "PAYTOCARD"
            },
            "transaction": {
                "merchant": {
                    "referenceNo": "trn-test-seck-1",
                    "status": "ERROR",
                    "customData": null,
                    "type": "AUTH",
                    "operation": "DIRECT",
                    "created_at": "2018-10-12 15:12:24",
                    "message": "Invalid Merchant",
                    "transactionId": "1011028-1539357144-1293"
                }
            },
            "refundable": false,
            "merchant": {
                "id": 1293,
                "name": "Seckin Merchant",
                "allowPartialRefund": true,
                "allowPartialCapture": true
            },
            "ipn": {
                "sent": true,
                "merchant": {
                    "transactionId": "1011028-1539357144-1293",
                    "referenceNo": "trn-test-seck-1",
                    "amount": 1500,
                    "currency": "RUB",
                    "date": 1539357144,
                    "code": "182",
                    "message": "Invalid Merchant",
                    "operation": "DIRECT",
                    "type": "AUTH",
                    "status": "ERROR",
                    "customData": null,
                    "chainId": "5bc0b9d89bdc5",
                    "paymentType": "PAYTOCARD",
                    "token": "1aa65e9ad2209d4757823503f7e631cdd92dfc41820d640db6f6e4bbc3d330dc",
                    "convertedAmount": 1500,
                    "convertedCurrency": "RUB",
                    "IPNUrl": "https://requestb.in/10bmd651",
                    "ipnType": "MERCHANTIPN"
                }
            }
        },
        {
            "customerInfo": {
                "number": "411111XXXXXX1111",
                "email": "seckin@bumin.io",
                "billingFirstName": "SECKIN",
                "billingLastName": "SEN"
            },
            "updated_at": "2018-10-12 15:11:42",
            "created_at": "2018-10-12 15:11:41",
            "fx": {
                "merchant": {
                    "originalAmount": 1500,
                    "originalCurrency": "RUB",
                    "convertedAmount": 1500,
                    "convertedCurrency": "RUB"
                }
            },
            "acquirer": {
                "id": 115,
                "name": "RoyalPay PayToCard",
                "code": "RP",
                "type": "PAYTOCARD"
            },
            "transaction": {
                "merchant": {
                    "referenceNo": "trn-test-seck-1",
                    "status": "ERROR",
                    "customData": null,
                    "type": "AUTH",
                    "operation": "DIRECT",
                    "created_at": "2018-10-12 15:11:41",
                    "message": "Invalid Merchant",
                    "transactionId": "1011027-1539357101-1293"
                }
            },
            "refundable": false,
            "merchant": {
                "id": 1293,
                "name": "Seckin Merchant",
                "allowPartialRefund": true,
                "allowPartialCapture": true
            },
            "ipn": {
                "sent": true,
                "merchant": {
                    "transactionId": "1011027-1539357101-1293",
                    "referenceNo": "trn-test-seck-1",
                    "amount": 1500,
                    "currency": "RUB",
                    "date": 1539357101,
                    "code": "182",
                    "message": "Invalid Merchant",
                    "operation": "DIRECT",
                    "type": "AUTH",
                    "status": "ERROR",
                    "customData": null,
                    "chainId": "5bc0b9ad38e8d",
                    "paymentType": "PAYTOCARD",
                    "token": "08e1ba6920a66e282ef7c50bd68af8a4cea973222992e775916cfb1fcb5b7233",
                    "convertedAmount": 1500,
                    "convertedCurrency": "RUB",
                    "IPNUrl": "https://requestb.in/10bmd651",
                    "ipnType": "MERCHANTIPN"
                }
            }
        },
        {
            "customerInfo": {
                "number": "411111XXXXXX1111",
                "email": "seckin@bumin.io",
                "billingFirstName": "SECKIN",
                "billingLastName": "SEN"
            },
            "updated_at": "2018-10-12 15:11:01",
            "created_at": "2018-10-12 15:10:59",
            "fx": {
                "merchant": {
                    "originalAmount": 1500,
                    "originalCurrency": "RUB",
                    "convertedAmount": 1500,
                    "convertedCurrency": "RUB"
                }
            },
            "acquirer": {
                "id": 115,
                "name": "RoyalPay PayToCard",
                "code": "RP",
                "type": "PAYTOCARD"
            },
            "transaction": {
                "merchant": {
                    "referenceNo": "trn-test-seck-1",
                    "status": "ERROR",
                    "customData": null,
                    "type": "AUTH",
                    "operation": "DIRECT",
                    "created_at": "2018-10-12 15:10:59",
                    "message": "Invalid Merchant",
                    "transactionId": "1011026-1539357059-1293"
                }
            },
            "refundable": false,
            "merchant": {
                "id": 1293,
                "name": "Seckin Merchant",
                "allowPartialRefund": true,
                "allowPartialCapture": true
            },
            "ipn": {
                "sent": true,
                "merchant": {
                    "transactionId": "1011026-1539357059-1293",
                    "referenceNo": "trn-test-seck-1",
                    "amount": 1500,
                    "currency": "RUB",
                    "date": 1539357059,
                    "code": "182",
                    "message": "Invalid Merchant",
                    "operation": "DIRECT",
                    "type": "AUTH",
                    "status": "ERROR",
                    "customData": null,
                    "chainId": "5bc0b9831a8b6",
                    "paymentType": "PAYTOCARD",
                    "token": "e408b47fb6107a1b4393b280526860a1265169d64f36e371ca1324af6efb564e",
                    "convertedAmount": 1500,
                    "convertedCurrency": "RUB",
                    "IPNUrl": "https://requestb.in/10bmd651",
                    "ipnType": "MERCHANTIPN"
                }
            }
        },
        {
            "customerInfo": {
                "number": "411111XXXXXX1111",
                "email": "seckin@bumin.io",
                "billingFirstName": "SECKIN",
                "billingLastName": "SEN"
            },
            "updated_at": "2018-10-12 15:09:35",
            "created_at": "2018-10-12 15:09:34",
            "fx": {
                "merchant": {
                    "originalAmount": 1500,
                    "originalCurrency": "RUB",
                    "convertedAmount": 1500,
                    "convertedCurrency": "RUB"
                }
            },
            "acquirer": {
                "id": 115,
                "name": "RoyalPay PayToCard",
                "code": "RP",
                "type": "PAYTOCARD"
            },
            "transaction": {
                "merchant": {
                    "referenceNo": "trn-test-seck-1",
                    "status": "ERROR",
                    "customData": null,
                    "type": "AUTH",
                    "operation": "DIRECT",
                    "created_at": "2018-10-12 15:09:34",
                    "message": "Invalid Merchant",
                    "transactionId": "1011025-1539356974-1293"
                }
            },*/
}
