package com.gamehub2.gamehub.ejb.Games;


import com.gamehub2.gamehub.entities.Games.GamePG;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

@Stateless
public class GamePGBean {
    private static final Logger LOG = Logger.getLogger(GameDetailsBean.class.getName());

    @PersistenceContext
    EntityManager entityManager;

    public List<GamePG> findAllGamePG() {
        LOG.info("\n** Entered findAllGamePG method **\n");
        try {
            List<GamePG> gamePGList = entityManager.createQuery("SELECT gp FROM GamePG gp", GamePG.class).getResultList();
            LOG.info("\n** Exited findAllGamePG method **\n");
            return gamePGList;
        } catch (Exception ex) {
            LOG.severe("\nError in findAllGamePG method! " + ex.getMessage() + "\n");
            throw new EJBException(ex);
        }
    }
    public GamePG.PGType getPGTypeByGameId(Long gameId){
        List<GamePG> allPG = findAllGamePG();
        for(GamePG pg : allPG){
            if(Objects.equals(pg.getGameDetails().getGameId(), gameId)){
                return pg.getType();
            }
        }
        return null;
    }

}
