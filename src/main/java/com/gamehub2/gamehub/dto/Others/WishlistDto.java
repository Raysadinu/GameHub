package com.gamehub2.gamehub.dto.Others;

import com.gamehub2.gamehub.entities.Games.Game;
import com.gamehub2.gamehub.entities.Users.User;

import java.util.List;

public class WishlistDto {
    Long wishlistId;
    User user;
    List<Game> games;
    double totalPrice;



    public WishlistDto(Long wishlistId, User user, List<Game> games) {
        this.wishlistId = wishlistId;
        this.user = user;
        this.games = games;
    }

    public WishlistDto() {
    }

    public Long getWishlistId() {
        return wishlistId;
    }
    public User getUser() {
        return user;
    }
    public List<Game> getGames() {
        return games;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
