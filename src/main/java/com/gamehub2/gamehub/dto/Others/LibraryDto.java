package com.gamehub2.gamehub.dto.Others;

import com.gamehub2.gamehub.entities.Games.Game;
import com.gamehub2.gamehub.entities.Users.User;

import java.util.List;

public class LibraryDto {
    Long libraryId;
    User user;
    List<Game> games;

    public LibraryDto(Long libraryId, User user, List<Game> games) {
        this.libraryId = libraryId;
        this.user = user;
        this.games = games;
    }


    public LibraryDto() {
    }

    public Long getLibraryId() {
        return libraryId;
    }

    public User getUser() {
        return user;
    }

    public List<Game> getGames() {
        return games;
    }
}
