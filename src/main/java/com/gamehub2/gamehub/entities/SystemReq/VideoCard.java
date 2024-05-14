package com.gamehub2.gamehub.entities.SystemReq;

import jakarta.persistence.*;

@Entity
public class VideoCard {
    @Id
    @GeneratedValue
    @Column(unique = true)
    private Long videoCardId;
    @Basic
    private String videoCardName;
    @Basic
    private int memory;
    @Basic
    private double performance;

    public Long getVideoCardId() {
        return videoCardId;
    }

    public void setVideoCardId(Long video_cardId) {
        this.videoCardId = video_cardId;
    }

    public String getVideoCardName() {
        return videoCardName;
    }

    public void setVideoCardName(String video_cardName) {
        this.videoCardName = video_cardName;
    }

    public double getPerformance() {
        return performance;
    }

    public void setPerformance(double performance) {
        this.performance = performance;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

}
