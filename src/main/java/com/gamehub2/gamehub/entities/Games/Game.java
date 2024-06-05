package com.gamehub2.gamehub.entities.Games;

import com.gamehub2.gamehub.entities.Others.*;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Game {
    @Id
    @GeneratedValue
    @Column(unique = true)
    private Long gameId;
    @Basic
    private String gameName;
    @OneToOne(mappedBy = "game", cascade = CascadeType.ALL)
    private PriceDetails priceDetails;
    @ManyToMany(mappedBy = "games",cascade = CascadeType.ALL)
    private List<Wishlist> wishlists;
    @ManyToMany(mappedBy = "games",cascade = CascadeType.ALL)
    private List<Cart> carts;
    @ManyToMany(mappedBy = "games",cascade = CascadeType.ALL)
    private List<Library> libraries;
    @ManyToMany(mappedBy = "games",cascade = CascadeType.ALL)
    private List<PaymentRequest> paymentRequests;
    @ManyToMany(mappedBy = "games",cascade = CascadeType.ALL)
    private List<Category> categories;
    @OneToOne(mappedBy = "game", cascade = CascadeType.ALL)
    private Picture pictures;
    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private List<GameScreenshot> screenshots;
    @OneToOne(mappedBy = "game", cascade = CascadeType.ALL)
    private Media videos;
    @OneToOne(mappedBy = "game", cascade = CascadeType.ALL)
    private Post post;


    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Picture getPictures() {
        return pictures;
    }

    public void setPictures(Picture pictures) {
        this.pictures = pictures;
    }

    public List<GameScreenshot> getScreenshots() {
        return screenshots;
    }

    public void setScreenshots(List<GameScreenshot> screenshots) {
        this.screenshots = screenshots;
    }

    public Media getVideos() {
        return videos;
    }

    public void setVideos(Media videos) {
        this.videos = videos;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

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

    public PriceDetails getPriceDetails() {
        return priceDetails;
    }

    public void setPriceDetails(PriceDetails priceDetails) {
        this.priceDetails = priceDetails;
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
    }
}

