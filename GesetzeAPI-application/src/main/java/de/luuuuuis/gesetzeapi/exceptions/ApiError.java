/*
 * Developed by Luuuuuis (@realluuuuuis)
 * Last modified 24.04.21, 23:40.
 * Copyright (c) 2021.
 */

package de.luuuuuis.gesetzeapi.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class ApiError {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss", timezone = "Europe/Berlin")
    private final LocalDateTime timestamp;
    private HttpStatus status;
    private String error;
    private String message;

    private ApiError() {
        timestamp = LocalDateTime.now();
    }

    ApiError(HttpStatus status, String message, Throwable ex) {
        this();
        this.status = status;
        this.message = message;
        this.error = ex.getClass().getSimpleName();
    }
}