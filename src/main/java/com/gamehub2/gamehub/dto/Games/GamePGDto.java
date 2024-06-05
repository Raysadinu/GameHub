package com.gamehub2.gamehub.dto.Games;

import com.gamehub2.gamehub.entities.Games.GameDetails;
import com.gamehub2.gamehub.entities.Games.GamePG;


public class GamePGDto {
    private Long id;
    private GamePG.PGType type;
    private GameDetails gameDetails;

    public GamePGDto(Long id, GamePG.PGType type, GameDetails gameDetails) {
        this.id = id;
        this.type = type;
        this.gameDetails = gameDetails;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GamePG.PGType getType() {
        return type;
    }

    public void setType(GamePG.PGType type) {
        this.type = type;
    }

    public GameDetails getGameDetails() {
        return gameDetails;
    }

    public void setGameDetails(GameDetails gameDetails) {
        this.gameDetails = gameDetails;
    }
}
