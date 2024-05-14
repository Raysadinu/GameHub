package com.gamehub2.gamehub.ejb.SystemReq;

import com.gamehub2.gamehub.common.SystemReq.PlatformDto;
import com.gamehub2.gamehub.ejb.Games.GameBean;
import com.gamehub2.gamehub.entities.SystemReq.Platform;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class PlatformBean {
    private static final Logger LOG = Logger.getLogger(GameBean.class.getName());
    @PersistenceContext
    EntityManager entityManager;
    public List<PlatformDto> findAllPlatforms() {
        LOG.info("\n** Entering findAllPlatforms method **\n");
        try {
            TypedQuery<Platform> typedQuery = entityManager.createQuery("SELECT p FROM Platform p", Platform.class);
            List<Platform> platforms = typedQuery.getResultList();
            LOG.info("\n** Exited findAllPlatforms method **\n");
            return copyPlatformsToDto(platforms);
        } catch (Exception ex) {
            LOG.info("\nError in findAllPlatforms method! " + ex.getMessage() + "\n");
            throw new EJBException(ex);
        }
    }

    public List<PlatformDto> copyPlatformsToDto(List<Platform> platforms) {
        LOG.info("\n** Entered copyPlatformsToDto method with list size: " + platforms.size() + "**\n");

        List<PlatformDto> listToReturn = new ArrayList<>();

        for (Platform currentPlatform : platforms) {
            PlatformDto platformDtoTemp = new PlatformDto(currentPlatform.getPlatformId(), currentPlatform.getPlatformName());
            listToReturn.add(platformDtoTemp);
        }

        LOG.info("\n** Exited copyPlatformsToDto method. **\n");
        return listToReturn;
    }

}
