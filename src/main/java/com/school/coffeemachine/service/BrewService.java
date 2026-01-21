package com.school.coffeemachine.service;

import com.school.coffeemachine.domain.Inventory;
import com.school.coffeemachine.domain.Recipe;
import com.school.coffeemachine.exception.InsufficientInventoryException;
import com.school.coffeemachine.repository.InventoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BrewService {
    private final RecipeService recipeService;
    private final InventoryRepository inventoryRepository;
    private final InventoryService inventoryService;

    public BrewService(RecipeService recipeService, InventoryRepository inventoryRepository, InventoryService inventoryService) {
        this.recipeService = recipeService;
        this.inventoryRepository = inventoryRepository;
        this.inventoryService = inventoryService;
    }

    @Transactional
    public void brew(Long recipeId) {
        Recipe recipe = recipeService.get(recipeId);
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
    }
}
