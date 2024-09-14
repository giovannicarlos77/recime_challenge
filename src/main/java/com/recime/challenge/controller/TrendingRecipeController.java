package com.recime.challenge.controller;

import com.recime.challenge.model.TrendingRecipe;
import com.recime.challenge.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class TrendingRecipeController {

    private final RecipeService recipeService;

    @Autowired
    public TrendingRecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/trending")
    public ResponseEntity<List<TrendingRecipe>> getTrendingRecipes() {
        List<TrendingRecipe> recipes = recipeService.getTrendingRecipes();
        return ResponseEntity.ok(recipes);
    }

    @GetMapping("/trending/filter")
    public ResponseEntity<?> getTrendingRecipesByDifficulty(@RequestParam(required = false) String difficulty) {
        if (difficulty == null || difficulty.isEmpty()) {
            return ResponseEntity.badRequest().body("A difficulty is required for filtering trending recipes");
        }
        List<TrendingRecipe> filteredRecipes = recipeService.getTrendingRecipesByDifficulty(difficulty);
        return ResponseEntity.ok(filteredRecipes);
    }
}