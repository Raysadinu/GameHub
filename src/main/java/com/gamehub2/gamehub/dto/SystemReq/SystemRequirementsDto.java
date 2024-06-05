package com.gamehub2.gamehub.dto.SystemReq;

import com.gamehub2.gamehub.entities.Games.GameDetails;

public class SystemRequirementsDto {
    private Long gameId;
    private GameDetails gameDetails;
    private MemoryDto memory;
    private ProcessorDto processor;
    private VideoCardDto videoCard;

    public SystemRequirementsDto() {
    }

    public SystemRequirementsDto(Long gameId, GameDetails gameDetails, MemoryDto memory, ProcessorDto processor, VideoCardDto videoCard) {
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

    public MemoryDto getMemory() {
        return memory;
    }

    public void setMemory(MemoryDto memory) {
        this.memory = memory;
    }

    public ProcessorDto getProcessor() {
        return processor;
    }

    public void setProcessor(ProcessorDto processor) {
        this.processor = processor;
    }

    public VideoCardDto getVideoCard() {
        return videoCard;
    }

    public void setVideoCard(VideoCardDto videoCard) {
        this.videoCard = videoCard;
    }
}
