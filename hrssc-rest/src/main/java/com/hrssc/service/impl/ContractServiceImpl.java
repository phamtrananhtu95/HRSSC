package com.hrssc.service.impl;

import com.hrssc.domain.Constant;
import com.hrssc.entities.*;
import com.hrssc.repository.*;
import com.hrssc.service.ContractService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
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

    @Autowired
    ContractVersionRepository contractVersionRepository;

    @Override
    public Interaction loadContract(int interactionId) throws NotFoundException{
        Interaction resultInteraction = interactionRepository.findById(interactionId);
        if(resultInteraction == null){
            throw new NotFoundException("Interaction not found");
        }
        return resultInteraction;
    }

    public Job loadJobContract(int jobId) throws NotFoundException{
        Job resultJob = jobRepository.findById(jobId);
        if(resultJob == null){
            throw new NotFoundException("Job not found");
        }
        if(resultJob.getContractId() == null){
            throw new NotFoundException("No contract for job");
        }
        return resultJob;
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
        ContractVersion contractVersion = new ContractVersion();
        contractVersion.setStartDate(dbContract.getStartDate());
        contractVersion.setEndDate(dbContract.getEndDate());
        contractVersion.setSalary(dbContract.getSalary());
        contractVersion.setAdditionalTerms(dbContract.getAdditionalTerms());
        contractVersion.setContractId(dbContract.getId());
        long currenttime  = System.currentTimeMillis();
        contractVersion.setDealDate(currenttime);
        contractVersionRepository.save(contractVersion);

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
        long curentdate =  System.currentTimeMillis();

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
        List<Job> checkList = new ArrayList<>();
        for (Job tmp: listJob) {
            if(tmp.getStatus() == Constant.JobStatus.CANCEL){
                checkList.add(tmp);
            }
        }
        listJob.removeAll(checkList);
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
    public String rejectOffer(int contractId){
        Contract rejectContract = contractRepository.findById(contractId);
        if (rejectContract != null){
            if(rejectContract.isAccepted()){
                return "Cant remove accepted contract!";
            }
            Interaction rejectInteraction = interactionRepository.findByContractId(rejectContract.getId());
            if(rejectInteraction != null){
                interactionRepository.deleteById(rejectInteraction.getId());
            }
            List<ContractVersion> contractVersionList = contractVersionRepository.findByContractId(rejectContract.getId());
            contractVersionRepository.deleteAll(contractVersionList);
            contractRepository.deleteById(rejectContract.getId());
        }
        return "Success!";
    }

    public List<ContractVersion> getContractVersionByContractId(int contractId){
        List<ContractVersion> resultList  = contractVersionRepository.findByContractId(contractId);
        return resultList;
    }

    @Transactional
    public boolean endContract(int jobId, int userId){
        Job job = jobRepository.findById(jobId);
        if(job == null){
            return false;
        }
        HumanResource resource = humanResourceRepository.getById(job.getHumanResourceId());
        if (resource == null){
            return false;
        }
        Project project = projectRepository.findById(job.getProjectId());
        if(project == null){
            return false;
        }
        boolean check = false;
        if (job.getStatus() == Constant.JobStatus.ON_GOING){
            check = true;
        }
        job.setStatus(Constant.JobStatus.CANCEL);
        long currentTime = System.currentTimeMillis();
        job.setLeaveDate(currentTime);
        jobRepository.save(job);

        project.setRequestStatus(Constant.RequestStatus.OPENNING);
        projectRepository.save(project);

        resource.setStatus(Constant.ResourceStatus.INACTIVE);
        humanResourceRepository.save(resource);
        if(project.getUserId() == userId) {
            return check;
        }
        return false;
    }

    public List<Job> loadAllContractResource(int userId){
        List<Job> jobList = new ArrayList<>();

        List<HumanResource> resourceList = humanResourceRepository.findByUserId(userId);
        if (!resourceList.isEmpty()) {
            for (HumanResource tmp : resourceList) {
                jobList.addAll(jobRepository.findByHumanResourceId(tmp.getId()));
            }
        }
        return jobList;
    }

    public List<Job> loadAllContractProject(int userId){
        List<Job> jobList = new ArrayList<>();

        List<Project> projectList = projectRepository.findByUserId(userId);
        if (!projectList.isEmpty()){
            for (Project tmp: projectList) {
                jobList.addAll(jobRepository.findByProjectId(tmp.getId()));
            }
        }

        return jobList;

    }
}
