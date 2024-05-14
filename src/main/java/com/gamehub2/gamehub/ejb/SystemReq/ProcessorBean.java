package com.gamehub2.gamehub.ejb.SystemReq;

import com.gamehub2.gamehub.common.SystemReq.ProcessorDto;
import com.gamehub2.gamehub.ejb.Games.GameBean;
import com.gamehub2.gamehub.entities.SystemReq.Processor;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class ProcessorBean {
    private static final Logger LOG = Logger.getLogger(GameBean.class.getName());
    @PersistenceContext
    EntityManager entityManager;
    public List<ProcessorDto> findAllProcessors() {
        LOG.info("\n** Entering findAllProcessors method **\n");
        try {
            TypedQuery<Processor> typedQuery = entityManager.createQuery("SELECT p FROM Processor p", Processor.class);
            List<Processor> processors = typedQuery.getResultList();
            LOG.info("\n** Exited findAllProcessors method **\n");
            return copyProcessorsToDto(processors);
        } catch (Exception ex) {
            LOG.info("\nError in findAllProcessors method! " + ex.getMessage() + "\n");
            throw new EJBException(ex);
        }
    }

    public List<ProcessorDto> copyProcessorsToDto(List<Processor> processors) {
        LOG.info("\n** Entered copyProcessorsToDto method with list size: " + processors.size() + "**\n");

        List<ProcessorDto> listToReturn = new ArrayList<>();

        for (Processor currentProcessor : processors) {
            ProcessorDto processorDtoTemp = new ProcessorDto(currentProcessor.getProcessorId(), currentProcessor.getProcessorName(), currentProcessor.getPerformance());
            listToReturn.add(processorDtoTemp);
        }

        LOG.info("\n** Exited copyProcessorsToDto method. **\n");
        return listToReturn;
    }

}
