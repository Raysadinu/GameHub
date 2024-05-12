package com.gamehub2.gamehub.common.Games;

import java.time.LocalDate;

public class GameDetailsDto {
    Long gameId;
    String gameName;
    LocalDate releaseDate;
    String publisher;
    String developer;
    String description;


    public GameDetailsDto(Long gameId, String gameName, LocalDate releaseDate, String publisher, String developer, String description) {
        this.gameId = gameId;
        this.gameName = gameName;
        this.releaseDate=releaseDate;
        this.publisher=publisher;
        this.developer=developer;
        this.description=description;
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

}

