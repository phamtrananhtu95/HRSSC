package com.hrssc.service.impl;

import com.hrssc.domain.Constant;
import com.hrssc.entities.HumanResource;
import com.hrssc.entities.Interaction;
import com.hrssc.repository.HumanResourceRepository;
import com.hrssc.repository.InteractionRepository;
import com.hrssc.service.InvitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("invitationService")
public class InvitationServiceImpl implements InvitationService {

    @Autowired
    InteractionRepository interactionRepository;

    @Autowired
    HumanResourceRepository humanResourceRepository;

    @Override
    public void inviteResource(Interaction interaction){
        Interaction interactionEntity = interactionRepository.findByHumanResourceIdAndProjectId(
                interaction.getHumanResourceId(),interaction.getProjectId());
        if(interactionEntity != null){
            if(interactionEntity.getType() == Constant.InteractionType.MATCH){
                interactionEntity.setType(Constant.InteractionType.INVITE);
                interactionRepository.save(interactionEntity);
            }
        }else{
            interaction.setType(Constant.InteractionType.INVITE);
            interactionRepository.save(interaction);
        }
    }


    public List<HumanResource> loadAllInvitationByManager(int managerId){
        List<HumanResource> resourceList = humanResourceRepository.getHumanResourcesByUserId(managerId);
        for(HumanResource resource: resourceList){
            List<Interaction> interactionList = new ArrayList<>();
            for(Interaction interaction: resource.getInteractionsById()){
                if(interaction.getType() == Constant.InteractionType.INVITE){
                    interactionList.add(interaction);
                }
            }
            resource.setInteractionsById(interactionList);
        }
        return resourceList;
    }

}
