package com.auth_app.auth_app_bakend.dtos;

import org.springframework.http.HttpStatus;

public record ErrorResponse(
        String message,
        HttpStatus code
) {


}
