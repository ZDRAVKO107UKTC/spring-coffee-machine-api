package com.school.coffeemachine.service;

import com.school.coffeemachine.api.dto.PaymentCurrency;
import com.school.coffeemachine.domain.Inventory;
import com.school.coffeemachine.domain.Recipe;
import com.school.coffeemachine.exception.InsufficientInventoryException;
import com.school.coffeemachine.exception.InsufficientPaymentException;
import com.school.coffeemachine.repository.InventoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class BrewService {
    private final RecipeService recipeService;
    private final InventoryRepository inventoryRepository;
    private final InventoryService inventoryService;
    private final ExchangeRateService exchangeRateService;

    public BrewService(
            RecipeService recipeService,
            InventoryRepository inventoryRepository,
            InventoryService inventoryService,
            ExchangeRateService exchangeRateService
    ) {
        this.recipeService = recipeService;
        this.inventoryRepository = inventoryRepository;
        this.inventoryService = inventoryService;
        this.exchangeRateService = exchangeRateService;
    }

    @Transactional
    public BigDecimal brew(Long recipeId, BigDecimal paymentAmount, PaymentCurrency paymentCurrency) {
        Recipe recipe = recipeService.get(recipeId);

        BigDecimal priceEur = recipe.getPrice().setScale(2, RoundingMode.HALF_UP);
        BigDecimal paidEur = exchangeRateService.toEur(paymentAmount, paymentCurrency);

        if (paidEur.compareTo(priceEur) < 0) {
            BigDecimal missing = priceEur.subtract(paidEur).setScale(2, RoundingMode.HALF_UP);
            throw new InsufficientPaymentException("Insufficient payment. Missing EUR: " + missing);
        }

        Inventory inv = inventoryService.getOrCreateDefault();

        if (inv.getCups() < 1) throw new InsufficientInventoryException("No cups available.");
        if (inv.getWaterMl() < recipe.getWaterMl()) throw new InsufficientInventoryException("Not enough waterMl.");
        if (inv.getMilkMl() < recipe.getMilkMl()) throw new InsufficientInventoryException("Not enough milkMl.");
        if (inv.getBeansG() < recipe.getBeansG()) throw new InsufficientInventoryException("Not enough beansG.");
        if (inv.getSugarG() < recipe.getSugarG()) throw new InsufficientInventoryException("Not enough sugarG.");

        inv.setCups(inv.getCups() - 1);
        inv.setWaterMl(inv.getWaterMl() - recipe.getWaterMl());
        inv.setMilkMl(inv.getMilkMl() - recipe.getMilkMl());
        inv.setBeansG(inv.getBeansG() - recipe.getBeansG());
        inv.setSugarG(inv.getSugarG() - recipe.getSugarG());

        inventoryRepository.save(inv);

        return paidEur.subtract(priceEur).setScale(2, RoundingMode.HALF_UP); // change in EUR
    }
}