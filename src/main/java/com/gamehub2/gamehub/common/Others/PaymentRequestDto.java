package com.gamehub2.gamehub.common.Others;

import com.gamehub2.gamehub.entities.Games.Game;
import com.gamehub2.gamehub.entities.Admins.Admin;
import com.gamehub2.gamehub.entities.Others.CardDetails;
import com.gamehub2.gamehub.entities.Users.User;

import java.util.List;

public class PaymentRequestDto {

    private Long paymentReqId;
    private List<Admin> admins;
    private List<Game> games;
    private CardDetails card;
    private User user;
    private String status;

    public PaymentRequestDto(Long paymentReqId, List<Admin> admins, List<Game> games, CardDetails card, User user, String status) {
        this.paymentReqId = paymentReqId;
        this.admins = admins;
        this.games = games;
        this.card = card;
        this.user = user;
        this.status = status;
    }

    public PaymentRequestDto(List<Admin> admins, List<Game> games, CardDetails card, User user, String status) {
        this.admins = admins;
        this.games = games;
        this.card = card;
        this.user = user;
        this.status = status;
    }

    public PaymentRequestDto() {
    }

    public Long getPaymentReqId() {
        return paymentReqId;
    }

    public List<Admin> getAdmins() {
        return admins;
    }

    public List<Game> getGames() {
        return games;
    }

    public CardDetails getCard() {
        return card;
    }

    public User getUser() {
        return user;
    }

    public String getStatus() {
        return status;
    }
}