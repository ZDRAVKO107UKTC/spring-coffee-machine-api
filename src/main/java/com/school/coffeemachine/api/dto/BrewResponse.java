package com.school.coffeemachine.api.dto;

import java.math.BigDecimal;

public class BrewResponse {
    private String status;
    private String message;
    private BigDecimal changeEur;

    public BrewResponse() {}

    public BrewResponse(String status, String message) {
        this.status = status;
        this.message = message;
        this.changeEur = null;
    }

    public BrewResponse(String status, String message, BigDecimal changeEur) {
        this.status = status;
        this.message = message;
        this.changeEur = changeEur;
    }

    public String getStatus() { return status; }
    public String getMessage() { return message; }
    public BigDecimal getChangeEur() { return changeEur; }
}