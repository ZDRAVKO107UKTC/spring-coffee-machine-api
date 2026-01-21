package com.school.coffeemachine.service;

import com.school.coffeemachine.api.dto.CreateRecipeRequest;
import com.school.coffeemachine.domain.Recipe;
import com.school.coffeemachine.exception.DuplicateRecipeNameException;
import com.school.coffeemachine.exception.NotFoundException;
import com.school.coffeemachine.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Recipe create(CreateRecipeRequest req) {
        if (recipeRepository.existsByNameIgnoreCase(req.getName())) {
            throw new DuplicateRecipeNameException("Recipe name already exists: " + req.getName());
        }
        Recipe r = new Recipe(
                req.getName().trim(),
                req.getPrice(),
                req.getWaterMl(),
                req.getMilkMl(),
                req.getBeansG(),
                req.getSugarG()
        );
        return recipeRepository.save(r);
    }

    public List<Recipe> list() {
        return recipeRepository.findAll();
    }

    public Recipe get(Long id) {
        return recipeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Recipe not found: " + id));
    }

    public void delete(Long id) {
        if (!recipeRepository.existsById(id)) throw new NotFoundException("Recipe not found: " + id);
        recipeRepository.deleteById(id);
    }
}
