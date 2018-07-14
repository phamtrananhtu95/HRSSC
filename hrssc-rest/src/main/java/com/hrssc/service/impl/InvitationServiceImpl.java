package com.hrssc.service.impl;

import com.hrssc.domain.Constant;
import com.hrssc.entities.*;
import com.hrssc.repository.*;
import com.hrssc.service.InvitationService;
import com.hrssc.service.ProjectManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("invitationService")
public class InvitationServiceImpl implements InvitationService {

    @Autowired
    InteractionRepository interactionRepository;

    @Autowired
    HumanResourceRepository humanResourceRepository;

    @Autowired
    JobRepository jobRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProjectManagementService projectManagementService;

    @Autowired
    ContractRepository contractRepository;

    @Override
    public String inviteResource(Interaction interaction){
        Job job = jobRepository.findByProjectIdAndHumanResourceId(interaction.getProjectId(),interaction.getHumanResourceId());
        if(job != null){
            return "This resource has already joined this project.";
        }
        Interaction interactionEntity = interactionRepository.findByHumanResourceIdAndProjectId(
                interaction.getHumanResourceId(),interaction.getProjectId());

        //Lưu Contract
        Contract contract = interaction.getContractByContractId();
        contract.setCreatedDate(System.currentTimeMillis()/1000);


        if(interactionEntity != null){
            String type = interactionEntity.getType();
            if(type.equals(Constant.InteractionType.MATCH)){
                contract = contractRepository.save(contract);
                interactionEntity.setType(Constant.InteractionType.INVITE);
                interactionEntity.setContractId(contract.getId());
                interactionRepository.save(interactionEntity);
            }else if(type.equals(Constant.InteractionType.INVITE)){
                return "This resource has been invited to this project";
            }else if(type.equals(Constant.InteractionType.APPLY)){
                return "This resource has been applied to this project";
            }
        }else{
            contract = contractRepository.save(contract);
            interaction.setType(Constant.InteractionType.INVITE);
            interaction.setContractId(contract.getId());
            interactionRepository.save(interaction);
        }
        return "Successfully Invited this Resource.";
    }

    @Override
    public List<HumanResource> loadAllInvitationByManager(int managerId){
        List<HumanResource> resourceList = humanResourceRepository.getHumanResourcesByUserId(managerId);
        List<HumanResource> resultList = new ArrayList<>();
        for(HumanResource resource: resourceList){
            List<Interaction> interactionList = new ArrayList<>();
            for(Interaction interaction: resource.getInteractionsById()){
                if(interaction.getType().equals(Constant.InteractionType.INVITE)){
                    interactionList.add(interaction);
                }
            }
            if(interactionList.size() > 0){
                resource.setInteractionsById(interactionList);
                resultList.add(resource);
            }
        }
        return resultList;
    }


    public List<Project> loadAllOfferByManager(int managerId){
        List<Project> projectList = projectManagementService.getProjectByManagerId(managerId);
        List<Project> resultList = new ArrayList<>();
        for(Project project: projectList){
            List<Interaction> interactionList = new ArrayList<>();
            for(Interaction interaction: project.getInteractionsById()){
                if(interaction.getType().equals(Constant.InteractionType.INVITE)){
                    interactionList.add(interaction);
                }
            }
            if(interactionList.size() > 0){
                project.setInteractionsById(interactionList);
                resultList.add(project);

            }
        }
        return resultList;
    }
    @Transactional
    @Override
    public String acceptInvitation(Interaction invitation){

        Project project = projectRepository.findById(invitation.getProjectId());
        if(project == null){
            return "Project Not Existed";
        }
        HumanResource resource = humanResourceRepository.getById(invitation.getHumanResourceId());
        if(resource == null){
            return "Resource Not Existed";
        }
        invitation = interactionRepository.findByHumanResourceIdAndProjectId(invitation.getHumanResourceId(),invitation.getProjectId());
        if(invitation != null){
            if(!invitation.getType().equals(Constant.InteractionType.INVITE)){
                return "Invitation not existed";
            }
        }else {
            return "Invitation not existed";
        }


        //1. Lưu Job
        Job job = new Job();
        job.setHumanResourceId(invitation.getHumanResourceId());
        job.setProjectId(invitation.getProjectId());
        job.setJoinedate(System.currentTimeMillis()/1000);

        if(job.getJoinedate() < project.getCreateDate()){
            job.setStatus(Constant.JobStatus.PENDING);
        }else if (job.getJoinedate() >= project.getCreateDate()) {
            job.setStatus(Constant.JobStatus.ON_GOING);
        }
        jobRepository.saveAndFlush(job);

        //2. Chuyển trạng thái của resource sang BUSY
        resource.setStatus(Constant.ResourceStatus.BUSY);
        humanResourceRepository.save(resource);

        //3. Xóa Tất cả các Matching, Invite và Appliance của resource
        interactionRepository.deleteByHumanResourceId(resource.getId());
        return "Invitation is Accepted";
    }
    public  String rejectInvitation(Interaction invitation){
        return "";
    }

    @Override
    public boolean checkInvited(int resourceId){
        Authentication authenticatedUser = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> userOptional = userRepository.findByUsername(authenticatedUser.getName());
        if(!userOptional.isPresent()){
            return false;
        }
        User user = userOptional.get();
        return  true;
    }
}
