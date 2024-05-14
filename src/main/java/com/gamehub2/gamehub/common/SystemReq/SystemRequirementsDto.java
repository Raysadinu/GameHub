package com.gamehub2.gamehub.common.SystemReq;

import com.gamehub2.gamehub.entities.Games.GameDetails;
import com.gamehub2.gamehub.entities.SystemReq.Memory;
import com.gamehub2.gamehub.entities.SystemReq.Processor;
import com.gamehub2.gamehub.entities.SystemReq.VideoCard;

public class SystemRequirementsDto {
    private Long gameId;
    private GameDetails gameDetails;
    private Memory memory;
    private Processor processor;
    private VideoCard videoCard;

    public SystemRequirementsDto() {
    }

    public SystemRequirementsDto(Long gameId, GameDetails gameDetails, Memory memory, Processor processor, VideoCard videoCard) {
        this.gameId = gameId;
        this.gameDetails = gameDetails;
        this.memory = memory;
        this.processor = processor;
        this.videoCard = videoCard;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public GameDetails getGameDetails() {
        return gameDetails;
    }

    public void setGameDetails(GameDetails gameDetails) {
        this.gameDetails = gameDetails;
    }

    public Memory getMemory() {
        return memory;
    }

    public void setMemory(Memory memory) {
        this.memory = memory;
    }

    public Processor getProcessor() {
        return processor;
    }

    public void setProcessor(Processor processor) {
        this.processor = processor;
    }

    public VideoCard getVideoCard() {
        return videoCard;
    }

    public void setVideoCard(VideoCard videoCard) {
        this.videoCard = videoCard;
    }
}
