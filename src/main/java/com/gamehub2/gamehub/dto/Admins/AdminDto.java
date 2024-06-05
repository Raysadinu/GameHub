package com.gamehub2.gamehub.dto.Admins;

import com.gamehub2.gamehub.entities.Others.PaymentRequest;
import com.gamehub2.gamehub.entities.Users.User;

import java.util.List;

public class AdminDto {

    String username;
    User user;
    List<PaymentRequest> paymentRequests;

    public AdminDto(String username, User user, List<PaymentRequest> paymentRequests) {
        this.username = username;
        this.user = user;
        this.paymentRequests = paymentRequests;
    }

    public AdminDto(String username, List<PaymentRequest> paymentRequests) {
        this.username = username;
        this.paymentRequests = paymentRequests;
    }

    public AdminDto(String username) {
        this.username = username;
    }

    public AdminDto() {
    }

    public String getUsername() {
        return username;
    }

    public User getUser() {
        return user;
    }

    public List<PaymentRequest> getPaymentRequests() {
        return paymentRequests;
    }
}
