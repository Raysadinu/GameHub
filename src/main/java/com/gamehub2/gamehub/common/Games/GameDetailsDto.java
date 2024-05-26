package com.gamehub2.gamehub.common.Games;
import com.gamehub2.gamehub.entities.Games.GameDetails;


import java.time.LocalDate;
import java.util.List;

public class GameDetailsDto {
    Long gameId;
    String gameName;
    LocalDate releaseDate;
    String publisher;
    String developer;
    String description;
    String storage;

    List<CategoryDto> categories;
    public GameDetailsDto(Long gameId, String gameName, LocalDate releaseDate, String publisher, String developer, String description, String storage) {
        this.gameId = gameId;
        this.gameName = gameName;
        this.releaseDate = releaseDate;
        this.publisher = publisher;
        this.developer = developer;
        this.description = description;
        this.storage = storage;
    }

    public GameDetailsDto(GameDetails gameDetails){
        this.gameId = gameDetails.getGameId();
        this.gameName = gameDetails.getGameName();
        this.releaseDate = gameDetails.getReleaseDate();
        this.publisher = gameDetails.getPublisher();
        this.developer = gameDetails.getDeveloper();
        this.description = gameDetails.getDescription();
        this.storage = gameDetails.getStorage();

    }

    public GameDetailsDto() {
    }

    public Long getGameId() {
        return gameId;
    }

    public String getGameName() {
        return gameName;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getDeveloper() {
        return developer;
    }

    public String getDescription() {
        return description;
    }

    public String getStorage() {return storage;}



    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public List<CategoryDto> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryDto> categories) {
        this.categories = categories;
    }


}

