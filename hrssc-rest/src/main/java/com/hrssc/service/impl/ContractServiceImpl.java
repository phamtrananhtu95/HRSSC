package com.hrssc.service.impl;

import com.hrssc.domain.Constant;
import com.hrssc.entities.*;
import com.hrssc.repository.*;
import com.hrssc.service.ContractService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("contractService")
public class ContractServiceImpl implements ContractService {

    @Autowired
    InteractionRepository interactionRepository;

    @Autowired
    ContractRepository contractRepository;

    @Autowired
    JobRepository jobRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    HumanResourceRepository humanResourceRepository;

    @Autowired
    ProjectRequirementRepository projectRequirementRepository;

    @Override
    public Interaction loadContract(int interactionId) throws NotFoundException{
        Interaction resultInteraction = interactionRepository.findById(interactionId);
        if(resultInteraction == null){
            throw new NotFoundException("Interaction not found");
        }
        return resultInteraction;
    }

    @Override
    public String changeOffer(Contract contract){
        Contract dbContract = contractRepository.findById(contract.getId());
        Interaction dbInteraction = interactionRepository.findByContractId(contract.getId());
        if(dbContract == null|| dbInteraction == null){
            return "Contract had been removed!";
        }
        if(dbContract.isAccepted()){
            return "Cant change accepted contract!";
        }
        if(dbContract.getLatestEditorId() == contract.getLatestEditorId()){
            return "Permission Not allow!";
        }
        dbContract.setStartDate(contract.getStartDate());
        dbContract.setEndDate(contract.getEndDate());
        dbContract.setSalary(contract.getSalary());
        dbContract.setAdditionalTerms(contract.getAdditionalTerms());
        dbContract.setLatestEditorId(contract.getLatestEditorId());
        contractRepository.save(dbContract);
        return "Success!";
    }

    @Transactional
    @Override
    public String acceptOffer(Contract contract){
        Contract dbContract = contractRepository.findById(contract.getId());
        Interaction acceptInteraction = interactionRepository.findByContractId(contract.getId());
        if(dbContract == null || acceptInteraction == null){
            return "Contract had been removed!";
        }
        if(dbContract.isAccepted()){
            return "Contract is accepted!";
        }
        if (dbContract.getLatestEditorId() == contract.getLatestEditorId()){
            return "Permission Not allow!";
        }
        HumanResource humanResource = humanResourceRepository.getById(acceptInteraction.getHumanResourceId());
        Project project = projectRepository.findById(acceptInteraction.getProjectId());
        if(humanResource.getStatus() == Constant.ResourceStatus.BUSY || humanResource.getStatus() == Constant.ResourceStatus.INACTIVE){
            return "Resource Busy or Inactive!";
        }
        if(project.getRequestStatus() == Constant.RequestStatus.REMOVED || project.getRequestStatus() == Constant.RequestStatus.CLOSED){
            return "Project full or remove!";
        }
        long curentdate =  System.currentTimeMillis()/1000;

        // Save job
        Job newjob = new Job();
        newjob.setHumanResourceId(acceptInteraction.getHumanResourceId());
        newjob.setProjectId(acceptInteraction.getProjectId());
        newjob.setJoinedate(curentdate);
        newjob.setStatus(project.getProcessStatus());
        newjob.setContractId(dbContract.getId());
        jobRepository.save(newjob);

        //Xoa acceptInteraction
        interactionRepository.deleteById(acceptInteraction.getId());

        //Update contract
        dbContract.setAcceptedDate(curentdate);
        dbContract.setAccepted(true);
        dbContract.setLatestEditorId(contract.getLatestEditorId());
        contractRepository.save(dbContract);

        //Update resource status, xoa interaction + contract cua resource
        humanResource.setStatus(Constant.ResourceStatus.BUSY);
        List<Interaction> resourceInteractionList = interactionRepository.findByHumanResourceId(humanResource.getId());
        for (Interaction tmp: resourceInteractionList) {
            interactionRepository.deleteById(tmp.getId());
            if(tmp.getContractId() != null) {
                contractRepository.deleteById(tmp.getContractId());
            }
        }

        //Kiem tra project da full hay chua
        List<ProjectRequirements> listProjectRequirement = projectRequirementRepository.findByProjectId(project.getId());
        int countSlot = 0;
        for (ProjectRequirements projectRequirement: listProjectRequirement) {
            countSlot += projectRequirement.getQuantity();
        }

        List<Job> listJob = jobRepository.findByProjectId(project.getId());
        //Full thi doi status, xoa interaction + contract cua project;
        if(countSlot == listJob.size()){
            project.setRequestStatus(Constant.RequestStatus.CLOSED);
            projectRepository.save(project);
            List<Interaction> projectInteractionList = interactionRepository.findByProjectId(project.getId());
            for (Interaction tmp: projectInteractionList) {
                interactionRepository.deleteById(tmp.getId());
                if(tmp.getContractId() != null) {
                    contractRepository.deleteById(tmp.getContractId());
                }
            }
        }
        return "Success!";
    }

    @Override
    public String rejectOffer(Contract contract){
        Contract rejectContract = contractRepository.findById(contract.getId());
        if (rejectContract != null){
            if(rejectContract.isAccepted()){
                return "Cant remove accepted contract!";
            }
            Interaction rejectInteraction = interactionRepository.findByContractId(rejectContract.getId());
            if(rejectInteraction != null){
                interactionRepository.deleteById(rejectInteraction.getId());
            }
            contractRepository.deleteById(rejectContract.getId());
        }
        return "Success!";
    }
}