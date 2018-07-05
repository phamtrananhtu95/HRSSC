package com.hrssc.service.impl;

import com.hrssc.domain.Constant;
import com.hrssc.entities.HumanResource;
import com.hrssc.entities.Interaction;
import com.hrssc.entities.Job;
import com.hrssc.entities.Project;
import com.hrssc.repository.HumanResourceRepository;
import com.hrssc.repository.InteractionRepository;
import com.hrssc.repository.JobRepository;
import com.hrssc.repository.ProjectRepository;
import com.hrssc.service.InvitationService;
import com.hrssc.service.ProjectManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

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
    ProjectManagementService projectManagementService;

    @Override
    public String inviteResource(Interaction interaction){
        Job job = jobRepository.findByProjectIdAndHumanResourceId(interaction.getProjectId(),interaction.getHumanResourceId());
        if(job != null){
            return "This resource has already joined this project.";
        }
        Interaction interactionEntity = interactionRepository.findByHumanResourceIdAndProjectId(
                interaction.getHumanResourceId(),interaction.getProjectId());
        if(interactionEntity != null){
            if(interactionEntity.getType().equals(Constant.InteractionType.MATCH)){
                interactionEntity.setType(Constant.InteractionType.INVITE);
                interactionRepository.save(interactionEntity);
            }
        }else{
            interaction.setType(Constant.InteractionType.INVITE);
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
                interactionList = new ArrayList<>();
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
        jobRepository.save(job);

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

}
