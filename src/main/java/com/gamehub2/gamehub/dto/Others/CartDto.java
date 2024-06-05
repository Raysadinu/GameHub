package com.gamehub2.gamehub.dto.Others;

import com.gamehub2.gamehub.entities.Games.Game;
import com.gamehub2.gamehub.entities.Users.User;

import java.util.List;

public class CartDto {
    Long cartId;
    User user;
    List<Game> games;
    double totalPrice;



    public CartDto(Long cartId, User user, List<Game> games) {
        this.cartId = cartId;
        this.user = user;
        this.games = games;
    }

    public CartDto() {
    }

    public Long getCartId() {
        return cartId;
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
