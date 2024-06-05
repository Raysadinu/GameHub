package com.gamehub2.gamehub.entities.Games;

import com.gamehub2.gamehub.entities.Others.Picture;
import jakarta.persistence.*;

@Entity
@Table(name = "game_screenshots")
public class GameScreenshot {
    @Id
    @GeneratedValue
    @Column(unique = true)
    private Long id;
    @OneToOne
    @MapsId
    @JoinColumn(name = "id" ,
            referencedColumnName = "id")
    private Picture picture;
    @ManyToOne
    @JoinColumn(name = "gameId")
    private Game game;

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
