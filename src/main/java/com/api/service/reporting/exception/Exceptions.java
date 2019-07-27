package com.api.service.reporting.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Exceptions {

    String message;

    HttpStatus code;
}
