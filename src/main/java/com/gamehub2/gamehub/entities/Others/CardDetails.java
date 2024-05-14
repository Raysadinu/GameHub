package com.gamehub2.gamehub.entities.Others;

import com.gamehub2.gamehub.entities.Users.User;
import jakarta.persistence.*;

import java.util.List;
@Entity
public class CardDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;

    @ManyToMany
    @JoinColumn(name = "username", referencedColumnName = "username")
    private List<User> user;

   @OneToMany(mappedBy = "card")
    private List<PaymentRequest> paymentRequests;
    @Basic
    private  String cardNumber;
    @Basic
    private String expirationDate;
    @Basic
    private String cardName;

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public List<PaymentRequest> getPaymentRequests() {
        return paymentRequests;
    }

    public void setPaymentRequests(List<PaymentRequest> paymentRequests) {
        this.paymentRequests = paymentRequests;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }
}