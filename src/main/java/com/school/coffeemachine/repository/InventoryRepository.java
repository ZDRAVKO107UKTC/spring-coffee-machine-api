package com.school.coffeemachine.repository;

import com.school.coffeemachine.domain.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {}
