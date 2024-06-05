package com.gamehub2.gamehub.entities.Others;


import com.gamehub2.gamehub.entities.Games.Game;
import com.gamehub2.gamehub.entities.Users.User;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long libraryId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username",
            referencedColumnName = "username")
    private User user;

    @ManyToMany
    @JoinColumn(name = "gameId",
            referencedColumnName = "gameId")
    private List<Game> games;

    public Long getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(Long libraryId) {
        this.libraryId = libraryId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }
}