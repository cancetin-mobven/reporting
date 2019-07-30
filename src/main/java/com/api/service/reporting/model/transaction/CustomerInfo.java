package com.api.service.reporting.model.transaction;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerInfo {

    /*  String number;
      String email;
      String billingFirstName;
      String billingLastName;
*/


      private Long id;
      private String created_at;
      private String updated_at;
      private String deleted_at ;
      private String number;
      private String expiryMonth;
      private String expiryYear;
      private String startMonth ;
      private String startYear ;
      private String issueNumber ;
      private String email;
      private String birthday;
      private String gender ;
      private String billingTitle ;
      private String billingFirstName;
      private String billingLastName;
      private String billingCompany ;
      private String billingAddress1;
      private String billingAddress2 ;
      private String billingCity;
      private String billingPostcode;
      private String billingState ;
      private String billingCountry;
      private String billingPhone ;
      private String billingFax ;
      private String shippingTitle ;
      private String shippingFirstName;
      private String shippingLastName;
      private String shippingCompany ;
      private String shippingAddress1;
      private String shippingAddress2 ;
      private String shippingCity;
      private String shippingPostcode;
      private String shippingState ;
      private String shippingCountry;
      private String shippingPhone ;
      private String shippingFax ;
      private String token ;



}
