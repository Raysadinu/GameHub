package com.gamehub2.gamehub.ejb.SystemReq;

import com.gamehub2.gamehub.dto.SystemReq.MemoryDto;
import com.gamehub2.gamehub.ejb.Games.GameBean;
import com.gamehub2.gamehub.entities.SystemReq.Memory;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class MemoryBean {
    private static final Logger LOG = Logger.getLogger(GameBean.class.getName());
    @PersistenceContext
    EntityManager entityManager;
    public List<MemoryDto> findAllMemory() {
        LOG.info("\n** Entering findAllMemory method **\n");
        try {
            TypedQuery<Memory> typedQuery = entityManager.createQuery("SELECT m FROM Memory m", Memory.class);
            List<Memory> memories = typedQuery.getResultList();
            LOG.info("\n** Exited findAllMemory method **\n");
            return copyMemoriesToDto(memories);
        } catch (Exception ex) {
            LOG.info("\nError in findAllMemory method! " + ex.getMessage() + "\n");
            throw new EJBException(ex);
        }
    }

    public List<MemoryDto> copyMemoriesToDto(List<Memory> memories) {
        LOG.info("\n** Entered copyMemoriesToDto method with list size: " + memories.size() + "**\n");

        List<MemoryDto> listToReturn = new ArrayList<>();

        for (Memory currentMemory : memories) {
            MemoryDto memoryDtoTemp = new MemoryDto(currentMemory.getMemoryId(), currentMemory.getMemory());
            listToReturn.add(memoryDtoTemp);
        }

        LOG.info("\n** Exited copyMemoriesToDto method. **\n");
        return listToReturn;
    }
}
