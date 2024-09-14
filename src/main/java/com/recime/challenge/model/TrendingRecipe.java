package com.recime.challenge.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TrendingRecipe {

    private int position;
    private String title;
    private String description;
    private String imageUrl;
    private String difficulty;

    public TrendingRecipe() {
    }

    @JsonCreator
    public TrendingRecipe(
            @JsonProperty("position") int position,
            @JsonProperty("title") String title,
            @JsonProperty("description") String description,
            @JsonProperty("imageUrl") String imageUrl,
            @JsonProperty("difficulty") String difficulty) {
        this.position = position;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.difficulty = difficulty;
    }

    // Getters and setters
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
}
