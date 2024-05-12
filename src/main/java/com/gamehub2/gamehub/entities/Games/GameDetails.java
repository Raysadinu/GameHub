package com.gamehub2.gamehub.entities.Games;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class GameDetails {
    @Id
    @GeneratedValue
    @Column(unique = true)
    private Long gameId;
    @OneToOne
    @MapsId
    @JoinColumn(name = "gameId", referencedColumnName = "gameId")
    private Game game;

    @Basic
    private LocalDate releaseDate;

    @Basic
    private String publisher;

    @Basic
    private String developer;
    @Basic
    @Lob
    private String description;

    @Basic
    private String gameName;

    public Long getGameId() {
        return gameId;
    }

    public Game getGame() {
        return game;
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

    public String getGameName() {
        return gameName;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public void setGame(Game game) {
        this.game = game;
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

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

}
