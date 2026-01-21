package com.school.coffeemachine.service;

import com.school.coffeemachine.api.dto.RefillInventoryRequest;
import com.school.coffeemachine.domain.Inventory;
import com.school.coffeemachine.repository.InventoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InventoryService {
    public static final long INVENTORY_ID = 1L;

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Transactional
    public Inventory getOrCreateDefault() {
        return inventoryRepository.findById(INVENTORY_ID).orElseGet(() -> {
            Inventory inv = new Inventory(INVENTORY_ID, 5000, 2000, 1000, 1000, 50);
            return inventoryRepository.save(inv);
        });
    }

    @Transactional
    public Inventory refill(RefillInventoryRequest req) {
        Inventory inv = getOrCreateDefault();
        inv.setWaterMl(inv.getWaterMl() + req.getWaterMl());
        inv.setMilkMl(inv.getMilkMl() + req.getMilkMl());
        inv.setBeansG(inv.getBeansG() + req.getBeansG());
        inv.setSugarG(inv.getSugarG() + req.getSugarG());
        inv.setCups(inv.getCups() + req.getCups());
        return inventoryRepository.save(inv);
    }
}
