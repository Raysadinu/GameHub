package com.gamehub2.gamehub.common.Others;

import com.gamehub2.gamehub.entities.Games.Game;
import com.gamehub2.gamehub.entities.Others.Media;

public class MediaDto {
    private Long id;
    private String link;
    private Game game;
    private Media.MediaType type;

    public MediaDto(Long id, String link) {
        this.id = id;
        this.link = link;
    }

    public MediaDto(Long id, String link, Game game, Media.MediaType type) {
        this.id = id;
        this.link = link;
        this.game = game;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Media.MediaType getType() {
        return type;
    }

    public void setType(Media.MediaType type) {
        this.type = type;
    }
}
