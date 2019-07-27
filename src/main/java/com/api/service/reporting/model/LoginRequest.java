package com.api.service.reporting.model;


import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LoginRequest {

   @NotBlank
   @NotNull
   @NotEmpty
    private String email;

    @NotBlank
    @NotNull
    @NotEmpty
    private String password;

}
