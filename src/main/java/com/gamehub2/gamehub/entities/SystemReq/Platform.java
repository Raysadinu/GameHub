package com.gamehub2.gamehub.entities.SystemReq;

import jakarta.persistence.*;

@Entity
public class Platform {
    @Id
    @GeneratedValue
    @Column(unique = true)
    private Long platformId;
    @Basic
    private String platformName;

    public Long getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Long platformId) {
        this.platformId = platformId;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }
}
