package com.gamehub2.gamehub.entities.Admins;

import com.gamehub2.gamehub.entities.Others.PaymentRequest;
import com.gamehub2.gamehub.entities.Users.User;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Admin {
    @Id
    @Column(unique = true)
    private String username; // Use username as the primary key
    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;

    @ManyToMany(mappedBy = "admins")
    static public List<PaymentRequest> paymentRequests = new ArrayList< PaymentRequest>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<PaymentRequest> getPaymentRequests() {
        return paymentRequests;
    }

}
