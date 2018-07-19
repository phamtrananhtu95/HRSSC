package com.hrssc.service.impl;

import com.hrssc.domain.Constant;
import com.hrssc.entities.*;
import com.hrssc.repository.*;
import com.hrssc.service.ApplianceService;
import com.hrssc.service.HumanResourceService;
import com.hrssc.service.ProjectManagementService;
import javassist.NotFoundException;
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

    @Autowired
    ContractRepository contractRepository;

    @Transactional
    public String applyToProject(Interaction interaction){
        Project checkProject = projectRepository.findById(interaction.getProjectId());
        if(checkProject == null){
            return "Project not found";
        }
        if(checkProject.getRequestStatus() == Constant.RequestStatus.CLOSED){
            return "Project full or closed";
        }
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
        if (applyInteraction.getType() == Constant.InteractionType.INVITE || applyInteraction.getType() == Constant.InteractionType.APPLY){
            return null;
        }
        applyInteraction.setType(Constant.InteractionType.APPLY);
        Contract dbContract = new Contract();
        if(interaction.getContractId() != null){
            dbContract = contractRepository.findById((int)interaction.getContractId());
        }
        Contract applyContract = interaction.getContractByContractId();
        if(dbContract.getCreatedDate() == 0) {
            long createdate = System.currentTimeMillis() / 1000;
            dbContract.setCreatedDate(createdate);
        }
        dbContract.setSalary(applyContract.getSalary());
        dbContract.setStartDate(applyContract.getStartDate());
        dbContract.setEndDate(applyContract.getEndDate());
        dbContract.setAdditionalTerms(applyContract.getAdditionalTerms());
        dbContract.setLatestEditorId(applyContract.getLatestEditorId());

        dbContract = contractRepository.save(dbContract);
        applyInteraction.setContractId(dbContract.getId());

        interactionRepository.save(applyInteraction);

        return "Success!!";
    }
    public List<Project> loadAllProjectAppliance(int managerId){
        List<Project> dblistProject = projectRepository.getProjectByUserId(managerId);
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
    public List<HumanResource> loadAllResourceAppliance(int managerId){
        List<HumanResource> dblistResource = humanResourceRepository.getHumanResourcesByUserId(managerId);
        List<HumanResource> rslistResource = new ArrayList<>();
        for (HumanResource tmp: dblistResource) {
            List<Interaction> interactionList = interactionRepository.findByHumanResourceIdAndType(tmp.getId(), Constant.InteractionType.APPLY);
            if(!interactionList.isEmpty()){
                tmp.setInteractionsById(interactionList);
                rslistResource.add(tmp);
            }
        }
        return rslistResource;
    }

    @Transactional
    public String acceptAppliance(Interaction interaction){
        Interaction acceptInterAction = interactionRepository.findById(interaction.getId());
        HumanResource humanResource = humanResourceRepository.getById(interaction.getHumanResourceId());
        Project project = projectRepository.findById(interaction.getProjectId());
        if(acceptInterAction == null || project == null || humanResource == null){
            return "Not found";
        }
        if(project.getRequestStatus() == Constant.RequestStatus.CLOSED){
            return "Project full or closed";
        }
        if(humanResource.getStatus() == Constant.ResourceStatus.BUSY || humanResource.getStatus() == Constant.ResourceStatus.INACTIVE){
            return "Resource busy or inactive";
        }
        long joindate = System.currentTimeMillis()/1000;

        Job newJob = new Job();

        newJob.setHumanResourceId(interaction.getHumanResourceId());
        newJob.setProjectId(interaction.getProjectId());
        newJob.setJoinedate(joindate);
        newJob.setStatus(project.getProcessStatus());
        newJob.setContractId(interaction.getContractId());
        Contract dbContract = contractRepository.findById((int) interaction.getContractId());
        dbContract.setAccepted(true);
        long acceptedDate = System.currentTimeMillis()/1000;
        dbContract.setAcceptedDate(acceptedDate);
        contractRepository.save(dbContract);
        jobRepository.save(newJob);
        interactionRepository.delete(acceptInterAction);

        humanResource.setStatus(Constant.ResourceStatus.BUSY);
        humanResourceService.changeResourceStatus(humanResource);
        List<Interaction> interactionList = interactionRepository.findByHumanResourceId(humanResource.getId());
        for (Interaction tmp: interactionList) {
            contractRepository.deleteById(tmp.getContractId());
        }
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
            List<Interaction> interactionList1 = interactionRepository.findByProjectId(project.getId());
            for (Interaction tmp: interactionList1) {
                contractRepository.deleteById(tmp.getContractId());
            }
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
