package com.gamehub2.gamehub.common.Users;

import java.time.LocalDate;
import java.util.Arrays;

public class UserDetailsDto {
    String username;
    String firstName;
    String lastName;
    LocalDate birthDate;
    String phoneNumber;
    String gender;
    String bio;
    String location;
    String nickname;

    public UserDetailsDto(String username, String firstName, String lastName, LocalDate birthDate, String phoneNumber, String gender, String bio, String location, String nickname) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.bio = bio;
        this.location = location;
        this.nickname = nickname;
    }

    public UserDetailsDto() {
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public String getBio() {
        return bio;
    }

    public String getLocation() {
        return location;
    }

    public String getNickname() {
        return nickname;
    }
}