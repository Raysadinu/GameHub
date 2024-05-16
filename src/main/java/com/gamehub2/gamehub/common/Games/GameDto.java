package com.gamehub2.gamehub.common.Games;

public class GameDto {

    Long gameId;
    String gameName;



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


}
