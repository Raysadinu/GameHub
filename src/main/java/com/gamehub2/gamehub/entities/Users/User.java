package com.gamehub2.gamehub.entities.Users;

import com.gamehub2.gamehub.entities.Others.*;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

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
    @Basic
    public String role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Follow> follower;
    @ManyToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<CardDetails> cardDetails;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Wishlist wishlists;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Cart carts;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Library libraries;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PaymentRequest> paymentRequests;

    public List<Follow> getFollower() {
        return follower;
    }

    public void setFollower(List<Follow> follower) {
        this.follower = follower;
    }

    public List<CardDetails> getCardDetails() {
        return cardDetails;
    }

    public void setCardDetails(List<CardDetails> cardDetails) {
        this.cardDetails = cardDetails;
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
    }
    public List<PaymentRequest> getPaymentRequests() {
        return paymentRequests;
    }

    public void setPaymentRequests(List<PaymentRequest> paymentRequests) {
        this.paymentRequests = paymentRequests;
    }

    public Library getLibraries() {
        return libraries;
    }

    public void setLibraries(Library libraries) {
        this.libraries = libraries;
    }

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


}