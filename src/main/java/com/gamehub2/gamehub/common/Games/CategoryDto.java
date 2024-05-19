package com.gamehub2.gamehub.common.Games;

import java.util.List;

public class CategoryDto {
    private Long categoryId;
    private String categoryName;
    private List<Long> gameIds;
    public CategoryDto() {
    }

    public CategoryDto(Long categoryId, String categoryName, List<Long> gameIds) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.gameIds = gameIds;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public List<Long> getGameIds() {
        return gameIds;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setGameIds(List<Long> gameIds) {
        this.gameIds = gameIds;
    }
}
