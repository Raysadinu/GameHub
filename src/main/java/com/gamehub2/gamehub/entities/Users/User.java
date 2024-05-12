package com.gamehub2.gamehub.entities.Users;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class User {
    @Id
    @Column(unique = true)
    private String username;
    @Basic
    private String email;
    @Basic
    private String password;
    private LocalDate dateJoined;


   /* @OneToOne(mappedBy = "user")
    private Wishlist wishlists;
    @OneToOne(mappedBy = "user")
    private Cart carts;
    @OneToMany(mappedBy = "user")
    private List<Friendship> friendships;
    @OneToOne(mappedBy = "user")
    private Library libraries;
    @OneToMany(mappedBy = "user")
    private List<PaymentRequest> paymentRequests;
    @ManyToMany(mappedBy = "user")
    private List<CardDetails> cardDetails;*/



    /*public List<PaymentRequest> getPaymentRequests() {
        return paymentRequests;
    }

    public void setPaymentRequests(List<PaymentRequest> paymentRequests) {
        this.paymentRequests = paymentRequests;
    }

    public List<CardDetails> getCardDetails() {
        return cardDetails;
    }

    public void setCardDetails(List<CardDetails> cardDetails) {
        this.cardDetails = cardDetails;
    }

    public Library getLibraries() {
        return libraries;
    }

    public void setLibraries(Library libraries) {
        this.libraries = libraries;
    }

    public List<Friendship> getFriendships() {
        return friendships;
    }

    public void setFriendships(List<Friendship> friendships) {

        this.friendships = friendships;
    }

    public Wishlist getWishlists() {

        return wishlists;
    }

    public void setWishlists(Wishlist wishlists) {

        this.wishlists = wishlists;
    }

    public Cart getCarts() {
        return carts;
    }

    public void setCarts(Cart carts) {
        this.carts = carts;
    }*/

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {

        this.username = username;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public LocalDate getDateJoined() {
        return dateJoined;
    }
    public void setDateJoined(LocalDate dateJoined) {
        this.dateJoined = dateJoined;
    }

}