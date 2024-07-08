package com.grupoc4.gestionEmpresa.error;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String details;

    public ErrorDetails(Date timestamp, String message, String details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    // Getters y setters
}
