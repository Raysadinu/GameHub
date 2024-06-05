package com.gamehub2.gamehub.entities.SystemReq;

import com.gamehub2.gamehub.entities.Games.Game;
import com.gamehub2.gamehub.entities.Games.GameDetails;
import jakarta.persistence.*;

@Entity
public class SystemRequirements {
    @Id
    @GeneratedValue
    @Column(unique = true)
    private Long gameId;
    @OneToOne
    @MapsId
    @JoinColumn(name = "gameId",
            referencedColumnName = "gameId")
    private GameDetails gameDetails;
    @OneToOne
    private Memory memory;
    @OneToOne
    private Processor processor;
    @OneToOne
    private VideoCard videoCard;

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
