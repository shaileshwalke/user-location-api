package com.dwp.userlocationapi.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    private String type ;
    private String message;
    private String service;
}
