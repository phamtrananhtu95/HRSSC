package com.hrssc.service.impl;

import com.hrssc.entities.Interaction;
import com.hrssc.repository.InteractionRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("contractService")
public class ContractServiceImpl {

    @Autowired
    InteractionRepository interactionRepository;

    public Interaction loadContract(int interactionId) throws NotFoundException{
        Interaction resultInteraction = interactionRepository.findById(interactionId);
        if(resultInteraction == null){
            throw new NotFoundException("Interaction not found");
        }
        return resultInteraction;
    }
}
