package com.gamehub2.gamehub.common.Games;

import com.gamehub2.gamehub.entities.Games.Category;
import com.gamehub2.gamehub.entities.Games.PriceDetails;

import java.util.List;
import java.util.stream.Collectors;

public class GameDto {

    Long gameId;
    String gameName;
    List<Category> categories;
    PriceDetails priceDetails;

    public GameDto(Long gameId, String gameName, List<Category> categories, PriceDetails priceDetails) {
        this.gameId = gameId;
        this.gameName = gameName;
        this.categories = categories;
        this.priceDetails = priceDetails;
    }

    public GameDto(Long gameId, String gameName) {
        this.gameId = gameId;
        this.gameName = gameName;
    }

    public GameDto() {
    }

    public Long getGameId() {
        return gameId;
    }

    public String getGameName() {
        return gameName;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public PriceDetails getPriceDetails() {
        return priceDetails;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public void setPriceDetails(PriceDetails priceDetails) {
        this.priceDetails = priceDetails;
    }

}
