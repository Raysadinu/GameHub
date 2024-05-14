package com.gamehub2.gamehub.entities.Games;

import jakarta.persistence.*;

@Entity
public class PriceDetails {
    @Id
    @GeneratedValue
    @Column(unique = true)
    private Long gameId;
    @OneToOne
    @MapsId
    @JoinColumn(name = "gameId", referencedColumnName = "gameId")
    private Game game;
    @Basic
    private double price;
    @Basic
    private double discount;
    @Basic
    private double discount_price;

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getDiscount_price() {
        return discount_price;
    }

    public void setDiscount_price(double discount_price) {
        this.discount_price = discount_price;
    }
}
