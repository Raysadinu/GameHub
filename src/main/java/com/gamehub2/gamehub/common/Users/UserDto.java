package com.gamehub2.gamehub.common.Users;

import java.time.LocalDate;

public class UserDto {

    String username;
    String email;
    String password;
    LocalDate dateJoined;
    public UserDto(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public UserDto(String username, String email, String password, LocalDate dateJoined) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.dateJoined = dateJoined;
    }

    public UserDto() {
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getDateJoined() {
        return dateJoined;
    }
}
