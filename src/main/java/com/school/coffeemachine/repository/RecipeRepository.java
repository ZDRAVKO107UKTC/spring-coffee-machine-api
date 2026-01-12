package com.school.coffeemachine.repository;

import com.school.coffeemachine.domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Optional<Recipe> findByNameIgnoreCase(String name);
    boolean existsByNameIgnoreCase(String name);
}
