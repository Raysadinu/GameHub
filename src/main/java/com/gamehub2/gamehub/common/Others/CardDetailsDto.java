package com.gamehub2.gamehub.common.Others;

import com.gamehub2.gamehub.entities.Others.PaymentRequest;
import com.gamehub2.gamehub.entities.Users.User;

import java.util.List;

public class CardDetailsDto {
    Long cardId ;
    List<User> user;
    List<PaymentRequest> paymentRequests;
    String cardNumber;
    String expirationDate;
    String cardName;

    public CardDetailsDto(Long cardId, List<User> user, List<PaymentRequest> paymentRequests, String cardNumber, String expirationDate, String cardName) {
        this.cardId = cardId;
        this.user = user;
        this.paymentRequests = paymentRequests;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cardName = cardName;
    }

    public CardDetailsDto(Long cardId, List<User> user, String cardNumber, String expirationDate, String cardName) {
        this.cardId = cardId;
        this.user = user;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cardName = cardName;
    }

    public CardDetailsDto() {
    }

    public Long getCardId() {
        return cardId;
    }

    public List<User> getUser() {
        return user;
    }

    public List<PaymentRequest> getPaymentRequests() {
        return paymentRequests;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public String getCardName() {
        return cardName;
    }
}
