package com.gamehub2.gamehub.entities.Others;

import com.gamehub2.gamehub.entities.Games.Game;
import com.gamehub2.gamehub.entities.Games.PriceDetails;
import com.gamehub2.gamehub.entities.Users.User;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wishlistId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;

    @ManyToMany
    @JoinColumn(name = "gameId", referencedColumnName = "gameId")
    private List<Game> games;

    @Column(name = "totalPrice")
    private double totalPrice;

    public Long getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(Long wishlistId) {
        this.wishlistId = wishlistId;
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

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
