package com.gamehub2.gamehub.entities.Users;

import com.gamehub2.gamehub.entities.Others.Picture;
import com.gamehub2.gamehub.entities.Users.User;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class UserDetails {
    @Id
    private String username;
    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "username",
            referencedColumnName = "username")
    private User user;
    @Basic
    private String firstName;
    @Basic
    private String lastName;
    private LocalDate birthDate;
    @Basic
    private String phoneNumber;
    @Basic
    private String gender;
    @Basic
    @Lob
    private String bio;
    @Basic
    private String location;
    @Basic
    private String nickname;
    @OneToOne(cascade = CascadeType.ALL)
    private Picture profilePicture;

    public Picture getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Picture profilePicture) {
        this.profilePicture = profilePicture;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getUsername() {
        return username;
    }

    public User getUser() {
        return user;
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