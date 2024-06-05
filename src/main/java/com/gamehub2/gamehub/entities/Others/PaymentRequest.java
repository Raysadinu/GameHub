package com.gamehub2.gamehub.entities.Others;

import com.gamehub2.gamehub.entities.Admins.Admin;
import com.gamehub2.gamehub.entities.Games.Game;
import com.gamehub2.gamehub.entities.Users.User;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class PaymentRequest {

    @Id
    @GeneratedValue
    @Column(unique = true)
    private  Long  paymentReqId;
    @ManyToMany
    @JoinColumn(name = "username",
            referencedColumnName = "username")
    private List<Admin> admins;
    @ManyToMany
    @JoinColumn(name = "gameId",
            referencedColumnName = "gameId")
    private List<Game> games;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private CardDetails card;
    @Column(name = "totalPrice")
    private double totalPrice;
    private LocalDateTime paymentDate;
    @ManyToOne
    private User user;
    @Enumerated(EnumType.STRING)
    private RequestStatus status;
    public enum RequestStatus {
        PENDING,
        ACCEPTED,
        REJECTED
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public Long getPaymentReqId() {
        return paymentReqId;
    }

    public void setPaymentReqId(Long paymentReqId) {
        this.paymentReqId = paymentReqId;
    }

    public List<Admin> getAdmins() {
        return admins;
    }

    public void setAdmins(List<Admin> admins) {
        this.admins = admins;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public CardDetails getCard() {
        return card;
    }

    public void setCard(CardDetails card) {
        this.card = card;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }
}
