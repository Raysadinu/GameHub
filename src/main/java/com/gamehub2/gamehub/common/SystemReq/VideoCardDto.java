package com.gamehub2.gamehub.common.SystemReq;

import com.gamehub2.gamehub.entities.SystemReq.Memory;
import com.gamehub2.gamehub.entities.SystemReq.VideoCard;

public class VideoCardDto {
    private Long videoCardId;
    private String videoCardName;
    private int memory;
    private double performance;
    public VideoCardDto(VideoCard videoCard){
        this.videoCardId=videoCard.getVideoCardId();
        this.videoCardName=videoCard.getVideoCardName();
        this.memory=videoCard.getMemory();
        this.performance=videoCard.getPerformance();
    }
    public VideoCardDto() {
    }

    public VideoCardDto(Long videoCardId, String videoCardName, int memory, double performance) {
        this.videoCardId = videoCardId;
        this.videoCardName = videoCardName;
        this.memory = memory;
        this.performance = performance;
    }

    public Long getVideoCardId() {
        return videoCardId;
    }

    public void setVideoCardId(Long videoCardId) {
        this.videoCardId = videoCardId;
    }

    public String getVideoCardName() {
        return videoCardName;
    }

    public void setVideoCardName(String videoCardName) {
        this.videoCardName = videoCardName;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public double getPerformance() {
        return performance;
    }

    public void setPerformance(double performance) {
        this.performance = performance;
    }

}
