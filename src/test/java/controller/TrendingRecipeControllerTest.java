package controller;

import com.recime.challenge.controller.TrendingRecipeController;
import com.recime.challenge.model.TrendingRecipe;
import com.recime.challenge.service.RecipeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@ExtendWith(MockitoExtension.class)
class TrendingRecipeControllerTest {

    @Mock
    private RecipeService recipeService;

    @InjectMocks
    private TrendingRecipeController trendingRecipeController;

    @Test
    void testGetTrendingRecipes() {
        List<TrendingRecipe> mockRecipes = List.of(
                new TrendingRecipe(1, "Burger", "Description", "https://image.url", "easy"),
                new TrendingRecipe(2, "Pizza", "Description", "https://image.url", "medium")
        );
        when(recipeService.getTrendingRecipes()).thenReturn(mockRecipes);

        ResponseEntity<List<TrendingRecipe>> response = trendingRecipeController.getTrendingRecipes();

        assertEquals(OK, response.getStatusCode());
        assertEquals(2, Objects.requireNonNull(response.getBody()).size());
        verify(recipeService, times(1)).getTrendingRecipes();
    }

    @Test
    void testGetTrendingRecipesByDifficulty() {
        ResponseEntity<?> response = trendingRecipeController.getTrendingRecipesByDifficulty(null);
        assertEquals(BAD_REQUEST, response.getStatusCode());
        assertEquals("A difficulty is required for filtering trending recipes", response.getBody());

        List<TrendingRecipe> mockRecipes = List.of(
                new TrendingRecipe(1, "Burger", "Description", "https://image.url", "easy")
        );
        when(recipeService.getTrendingRecipesByDifficulty("easy")).thenReturn(mockRecipes);

        ResponseEntity<?> validResponse = trendingRecipeController.getTrendingRecipesByDifficulty("easy");

        assertEquals(OK, validResponse.getStatusCode());
        assertEquals(1, ((List<?>) Objects.requireNonNull(validResponse.getBody())).size());
        verify(recipeService, times(1)).getTrendingRecipesByDifficulty("easy");
    }
}

