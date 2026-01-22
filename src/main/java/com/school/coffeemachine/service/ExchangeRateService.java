package com.school.coffeemachine.service;

import com.school.coffeemachine.api.dto.PaymentCurrency;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class ExchangeRateService {
    // Fixed peg: 1 EUR = 1.95583 BGN  =>  1 BGN = 1 / 1.95583 EUR
    private static final BigDecimal BGN_PER_EUR = new BigDecimal("1.95583");
    private static final BigDecimal EUR_PER_BGN = BigDecimal.ONE.divide(BGN_PER_EUR, 10, RoundingMode.HALF_UP);

    public BigDecimal toEur(BigDecimal amount, PaymentCurrency currency) {
        if (currency == PaymentCurrency.EUR) {
            return amount.setScale(2, RoundingMode.HALF_UP);
        }
        if (currency == PaymentCurrency.BGN) {
            return amount.multiply(EUR_PER_BGN).setScale(2, RoundingMode.HALF_UP);
        }
        throw new IllegalArgumentException("Unsupported currency: " + currency);
    }
}