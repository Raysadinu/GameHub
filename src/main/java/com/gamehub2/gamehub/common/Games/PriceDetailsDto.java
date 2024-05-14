package com.gamehub2.gamehub.common.Games;

public class PriceDetailsDto {
    Long gameId;
    double price;
    double discount;
    double discount_price;
    public PriceDetailsDto() {
    }
    public PriceDetailsDto(Long gameId, double price, double discount, double discount_price) {
        this.gameId = gameId;
        this.price = price;
        this.discount = discount;
        this.discount_price = discount_price;
    }
    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
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
