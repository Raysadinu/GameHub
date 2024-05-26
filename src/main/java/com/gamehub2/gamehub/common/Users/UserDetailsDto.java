package com.gamehub2.gamehub.common.Users;

import com.gamehub2.gamehub.entities.Others.Picture;

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
    Picture profilePicture;

    public UserDetailsDto(String username, String firstName, String lastName, LocalDate birthDate, String phoneNumber, String gender, String bio, String location, String nickname, Picture profilePicture) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.bio = bio;
        this.location = location;
        this.nickname = nickname;
        this.profilePicture = profilePicture;
    }

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

    public Picture getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Picture profilePicture) {
        this.profilePicture = profilePicture;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setUsername(String username) {
        this.username = username;
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