package com.hrssc.service.impl;

import com.hrssc.domain.Constant;
import com.hrssc.entities.*;
import com.hrssc.repository.*;
import com.hrssc.service.ApplianceService;
import com.hrssc.service.HumanResourceService;
import com.hrssc.service.ProjectManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("applianceService")
public class ApplianceServiceImpl implements ApplianceService {

    @Autowired
    InteractionRepository interactionRepository;

    @Autowired
    JobRepository jobRepository;

    @Autowired
    HumanResourceRepository humanResourceRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    HumanResourceService humanResourceService;

    @Autowired
    ProjectManagementService projectManagementService;

    @Autowired
    ProjectRequirementRepository projectRequirementRepository;

    public String applyToProject(Interaction interaction){
        Job checkJob = jobRepository.findByProjectIdAndHumanResourceId(interaction.getProjectId(), interaction.getHumanResourceId());
        if(checkJob != null){
            return "This resource has already joined this project";
        }
        Interaction applyInteraction = interactionRepository.findByProjectIdAndHumanResourceId(interaction.getProjectId(), interaction.getHumanResourceId());
        if(applyInteraction == null){
            applyInteraction = new Interaction();
            applyInteraction.setHumanResourceId(interaction.getHumanResourceId());
            applyInteraction.setProjectId(interaction.getProjectId());
        }
        if (applyInteraction.getType() == Constant.InteractionType.INVITE){
            return null;
        }
        applyInteraction.setType(Constant.InteractionType.APPLY);
        interactionRepository.save(applyInteraction);

        return "Success!!";

    }
    public List<Project> loadAllInteraction(int managerID){
        List<Project> dblistProject = projectRepository.getProjectByUserId(managerID);
        List<Project> resultListProject = new ArrayList<>();
        for (Project project: dblistProject) {
            List<Interaction> interactionList = interactionRepository.findByProjectIdAndType(project.getId(), Constant.InteractionType.APPLY);
            if (!interactionList.isEmpty())    {
                project.setInteractionsById(interactionList);
                resultListProject.add(project);
            }


        }
        return resultListProject;

    }

    @Transactional
    public String acceptAppliance(Interaction interaction){
        Interaction acceptInterAction = interactionRepository.findById(interaction.getId());
        Optional<HumanResource> humanResourceOptional = humanResourceRepository.findById(interaction.getHumanResourceId());
        Project project = projectRepository.findById(interaction.getProjectId());
        if(acceptInterAction == null){
            return "Not found";
        }
        long joindate = System.currentTimeMillis()/1000;

        Job newJob = new Job();

        newJob.setHumanResourceId(interaction.getHumanResourceId());
        newJob.setProjectId(interaction.getProjectId());
        newJob.setJoinedate(joindate);
        newJob.setStatus(project.getProcessStatus());
        jobRepository.save(newJob);


        HumanResource humanResource = humanResourceOptional.get();
        humanResource.setStatus(Constant.ResourceStatus.BUSY);
        humanResourceService.changeResourceStatus(humanResource);
        interactionRepository.deleteByHumanResourceId(humanResource.getId());

        List<ProjectRequirements> listProjectRequirement = projectRequirementRepository.findByProjectId(project.getId());
        int countSlot = 0;
        for (ProjectRequirements projectRequirement: listProjectRequirement) {
            countSlot += projectRequirement.getQuantity();
        }

        List<Job> listJob = jobRepository.findByProjectId(project.getId());
        if(countSlot == listJob.size()){
            project.setRequestStatus(Constant.RequestStatus.CLOSED);
            projectManagementService.updateStatus(project);
            interactionRepository.deleteByProjectId(project.getId());
        }
        return "Success!!";
    }

    public String rejectAppliance(Interaction interaction){
        Interaction rejectInteraction = interactionRepository.findById(interaction.getId());
        if(rejectInteraction != null) {
            interactionRepository.delete(rejectInteraction);
        }
        return "Success";
    }

}
