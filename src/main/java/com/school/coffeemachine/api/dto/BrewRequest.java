package com.school.coffeemachine.api.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class BrewRequest {
    @NotNull
    private Long recipeId;

    @NotNull
    @DecimalMin(value = "0.01", message = "paymentAmount must be at least 0.01")
    private BigDecimal paymentAmount;

    @NotNull
    private PaymentCurrency paymentCurrency;

    public Long getRecipeId() { return recipeId; }
    public void setRecipeId(Long recipeId) { this.recipeId = recipeId; }

    public BigDecimal getPaymentAmount() { return paymentAmount; }
    public void setPaymentAmount(BigDecimal paymentAmount) { this.paymentAmount = paymentAmount; }

    public PaymentCurrency getPaymentCurrency() { return paymentCurrency; }
    public void setPaymentCurrency(PaymentCurrency paymentCurrency) { this.paymentCurrency = paymentCurrency; }
}
