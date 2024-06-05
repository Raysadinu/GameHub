package com.gamehub2.gamehub.dto.Games;

import com.gamehub2.gamehub.entities.Games.Game;
import com.gamehub2.gamehub.entities.Others.Picture;

public class GameScreenshotDto {
    private Long id;
    private Game game;
    private Picture picture;

    public GameScreenshotDto(Long id, Game game, Picture picture) {
        this.id = id;
        this.game = game;
        this.picture = picture;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }
}
