package com.gamehub2.gamehub.entities.Others;

import com.gamehub2.gamehub.entities.Games.Game;
import jakarta.persistence.*;

@Entity
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String link;
    @OneToOne
    @JoinColumn(name = "gameId",
            referencedColumnName = "gameId")
    private Game game;
    @Enumerated(EnumType.STRING)
    private Media.MediaType type;
    public enum MediaType {
        GAME_VIDEO
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

    public MediaType getType() {
        return type;
    }

    public void setType(MediaType type) {
        this.type = type;
    }
}
