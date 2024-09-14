package com.recime.challenge.service;

import com.recime.challenge.model.TrendingRecipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecipeService {

    private final RestTemplate restTemplate;
    private final String wireMockUrl = "http://localhost:8089/recipes"; // Hardcoded for simplicity

    @Autowired
    public RecipeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<TrendingRecipe> getTrendingRecipes() {
        ResponseEntity<Map<String, List<TrendingRecipe>>> response = restTemplate.exchange(
                wireMockUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );

        List<TrendingRecipe> recipes = Objects.requireNonNull(response.getBody()).get("trendingRecipes");  // Extract the list

        return recipes.stream()
                .sorted(Comparator.comparingInt(TrendingRecipe::getPosition))  // Sort by position
                .collect(Collectors.toList());
    }

    public List<TrendingRecipe> getTrendingRecipesByDifficulty(String difficulty) {
        ResponseEntity<Map<String, List<TrendingRecipe>>> response = restTemplate.exchange(
                wireMockUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );

        List<TrendingRecipe> recipes = Objects.requireNonNull(response.getBody()).get("trendingRecipes");

        return recipes.stream()
                .filter(recipe -> recipe.getDifficulty().equalsIgnoreCase(difficulty))  // Filter by difficulty
                .sorted(Comparator.comparingInt(TrendingRecipe::getPosition))  // Sort by position
                .collect(Collectors.toList());
    }
}