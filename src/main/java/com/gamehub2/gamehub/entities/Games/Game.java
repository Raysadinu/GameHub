package com.gamehub2.gamehub.entities.Games;

import jakarta.persistence.*;

@Entity
public class Game {
    @Id
    @GeneratedValue
    @Column(unique = true)
    private Long gameId;
    @Basic
    private String gameName;
    private boolean inWishlist;
    private boolean inCart;
    private boolean inLibrary;


    /*@ManyToMany(mappedBy = "games")
    private List<Wishlist> wishlists;

    @ManyToMany(mappedBy = "games")
    private List<Library> libraries;

    @ManyToMany(mappedBy = "games")
    private List<Cart> carts;

    @ManyToMany(mappedBy = "games")
    private List<PaymentRequest> paymentRequests;



    public List<PaymentRequest> getPaymentRequests() {
        return paymentRequests;
    }

    public void setPaymentRequests(List<PaymentRequest> paymentRequests) {
        this.paymentRequests = paymentRequests;
    }

    public List<Library> getLibraries() {
        return libraries;
    }

    public void setLibraries(List<Library> libraries) {
        this.libraries = libraries;
    }

    public List<Wishlist> getWishlists() {

        return wishlists;
    }

    public void setWishlists(List<Wishlist> wishlists) {

        this.wishlists = wishlists;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }*/

    public Long getGameId() {

        return gameId;
    }

    public String getGameName() {

        return gameName;
    }

    public void setGameId(Long gameId) {

        this.gameId = gameId;
    }

    public void setGameName(String gameName) {

        this.gameName = gameName;
    }

    public boolean isInWishlist() {
        return inWishlist;
    }

    public void setInWishlist(boolean inWishlist) {
        this.inWishlist = inWishlist;
    }

    public boolean isInCart() {
        return inCart;
    }

    public void setInCart(boolean inCart) {
        this.inCart = inCart;
    }

    public boolean isInLibrary() {
        return inLibrary;
    }

    public void setInLibrary(boolean inLibrary) {
        this.inLibrary = inLibrary;
    }
}

