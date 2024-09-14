package service;

import com.recime.challenge.model.TrendingRecipe;
import com.recime.challenge.service.RecipeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

;

@ExtendWith(MockitoExtension.class)
class RecipeServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private RecipeService recipeService;

    @Test
    void testGetTrendingRecipes() {
        List<TrendingRecipe> mockRecipes = List.of(
                new TrendingRecipe(1, "Burger", "Description", "https://image.url", "easy"),
                new TrendingRecipe(2, "Pizza", "Description", "https://image.url", "medium")
        );

        Map<String, List<TrendingRecipe>> responseMap = new HashMap<>();
        responseMap.put("trendingRecipes", mockRecipes);

        ResponseEntity<Map<String, List<TrendingRecipe>>> mockResponse = ResponseEntity.ok(responseMap);

        ParameterizedTypeReference<Map<String, List<TrendingRecipe>>> responseType =
                new ParameterizedTypeReference<>() {};

        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), isNull(), eq(responseType)))
                .thenReturn(mockResponse);

        List<TrendingRecipe> result = recipeService.getTrendingRecipes();

        assertEquals(2, result.size());
        verify(restTemplate, times(1)).exchange(anyString(), eq(HttpMethod.GET), isNull(), eq(responseType));
    }

    @Test
    void testGetTrendingRecipesByDifficulty() {
        List<TrendingRecipe> mockRecipes = List.of(
                new TrendingRecipe(1, "Burger", "Description", "https://image.url", "easy"),
                new TrendingRecipe(2, "Pizza", "Description", "https://image.url", "medium")
        );

        Map<String, List<TrendingRecipe>> responseMap = new HashMap<>();
        responseMap.put("trendingRecipes", mockRecipes);

        ResponseEntity<Map<String, List<TrendingRecipe>>> mockResponse = ResponseEntity.ok(responseMap);

        ParameterizedTypeReference<Map<String, List<TrendingRecipe>>> responseType =
                new ParameterizedTypeReference<>() {};

        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), isNull(), eq(responseType)))
                .thenReturn(mockResponse);

        List<TrendingRecipe> result = recipeService.getTrendingRecipesByDifficulty("easy");

        assertEquals(1, result.size());
        assertEquals("Burger", result.getFirst().getTitle());
        verify(restTemplate, times(1)).exchange(anyString(), eq(HttpMethod.GET), isNull(), eq(responseType));
    }
}