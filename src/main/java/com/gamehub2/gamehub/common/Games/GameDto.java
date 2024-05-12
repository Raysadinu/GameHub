package com.gamehub2.gamehub.common.Games;

public class GameDto {

    Long gameId;
    String gameName;
    boolean inWishlist;
    boolean inCart;
    boolean inLibrary;

    public GameDto(Long gameId, String gameName, boolean inWishlist, boolean inCart, boolean inLibrary) {
        this.gameId = gameId;
        this.gameName = gameName;
        this.inWishlist = inWishlist;
        this.inCart = inCart;
        this.inLibrary = inLibrary;
    }

    public GameDto(Long gameId, String gameName) {
        this.gameId = gameId;
        this.gameName = gameName;
    }

    public GameDto() {
    }

    public Long getGameId() {
        return gameId;
    }

    public String getGameName() {
        return gameName;
    }

    public boolean isInWishlist() {
        return inWishlist;
    }

    public boolean isInCart() {
        return inCart;
    }

    public boolean isInLibrary() {
        return inLibrary;
    }
}
