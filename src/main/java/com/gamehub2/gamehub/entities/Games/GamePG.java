package com.gamehub2.gamehub.entities.Games;


import jakarta.persistence.*;



@Entity
public class GamePG {
    @Id
    @GeneratedValue
    @Column(unique = true)
    private Long id;
    @Enumerated(EnumType.STRING)
    private PGType type;

    public enum PGType {
        PEGI3,
        PEGI7,
        PEGI12,
        PEGI16,
        PEGI18
    }
    @OneToOne
    @JoinColumn(name = "gameId",
            referencedColumnName = "gameId")
    private GameDetails gameDetails;


    public GameDetails getGameDetails() {
        return gameDetails;
    }

    public void setGameDetails(GameDetails gameDetails) {
        this.gameDetails = gameDetails;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PGType getType() {
        return type;
    }

    public void setType(PGType type) {
        this.type = type;
    }
}
