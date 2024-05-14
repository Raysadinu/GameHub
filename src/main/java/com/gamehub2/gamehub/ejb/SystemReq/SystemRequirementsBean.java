package com.gamehub2.gamehub.ejb.SystemReq;

import com.gamehub2.gamehub.common.Games.GameDetailsDto;
import com.gamehub2.gamehub.common.SystemReq.MemoryDto;
import com.gamehub2.gamehub.common.SystemReq.ProcessorDto;
import com.gamehub2.gamehub.common.SystemReq.SystemRequirementsDto;
import com.gamehub2.gamehub.common.SystemReq.VideoCardDto;
import com.gamehub2.gamehub.ejb.Games.GameBean;
import com.gamehub2.gamehub.entities.SystemReq.SystemRequirements;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class SystemRequirementsBean {
    private static final Logger LOG = Logger.getLogger(GameBean.class.getName());
    @PersistenceContext
    EntityManager entityManager;
    public List<SystemRequirementsDto> findAllSystemRequirements() {
        LOG.info("\n** Entering findAllSystemRequirements method **\n");
        try {
            TypedQuery<SystemRequirements> typedQuery = entityManager.createQuery("SELECT sr FROM SystemRequirements sr", SystemRequirements.class);
            List<SystemRequirements> systemRequirementsList = typedQuery.getResultList();
            LOG.info("\n** Exited findAllSystemRequirements method **\n");
            return copySystemRequirementsToDto(systemRequirementsList);
        } catch (Exception ex) {
            LOG.info("\nError in findAllSystemRequirements method! " + ex.getMessage() + "\n");
            throw new EJBException(ex);
        }
    }

    public List<SystemRequirementsDto> copySystemRequirementsToDto(List<SystemRequirements> systemRequirementsList) {
        LOG.info("\n** Entered copySystemRequirementsToDto method with list size: " + systemRequirementsList.size() + "**\n");

        List<SystemRequirementsDto> listToReturn = new ArrayList<>();

        for (SystemRequirements currentSystemRequirement : systemRequirementsList) {
            MemoryDto memoryDto = new MemoryDto(currentSystemRequirement.getMemory());
            ProcessorDto processorDto = new ProcessorDto(currentSystemRequirement.getProcessor());
            VideoCardDto videoCardDto = new VideoCardDto(currentSystemRequirement.getVideoCard());
            GameDetailsDto gameDetailsDto = new GameDetailsDto(currentSystemRequirement.getGameDetails());

            SystemRequirementsDto systemRequirementsDtoTemp = new SystemRequirementsDto(currentSystemRequirement.getGameId(), currentSystemRequirement.getGameDetails(), currentSystemRequirement.getMemory(),currentSystemRequirement.getProcessor(), currentSystemRequirement.getVideoCard());
            listToReturn.add(systemRequirementsDtoTemp);
        }

        LOG.info("\n** Exited copySystemRequirementsToDto method. **\n");
        return listToReturn;
    }
}
