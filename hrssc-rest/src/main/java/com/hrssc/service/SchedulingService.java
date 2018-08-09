package com.hrssc.service;

import com.hrssc.domain.Constant;
import com.hrssc.entities.Job;
import com.hrssc.entities.Project;
import com.hrssc.repository.JobRepository;
import com.hrssc.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service("schedulingService")
public class SchedulingService {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    JobRepository jobRepository;

    @Autowired
    ProjectManagementService projectManagementService;

    Logger logger = Logger.getLogger(SchedulingService.class.getName());
    //@Scheduled(fixedRate = 1000*60*60*24)
    //@Scheduled(cron = "0 30 6 * * ?)
    private void updateProjectStatus() throws Exception{
        List<Project> pendingProjectList = projectRepository.findByProcessStatusNot(Constant.ProjectProcess.FINISHED);
        if(pendingProjectList == null || pendingProjectList.size() == 0){
            return;
        }
        long currentTime = System.currentTimeMillis();
        for(Project pendingProject: pendingProjectList){
            long projectEndTime = pendingProject.getEndDate();
            if(currentTime >= projectEndTime){
                projectManagementService.closeFinishedProject(pendingProject.getId());
                logger.log(Level.INFO, "Successfully FINISHED a project. Project ID:" +pendingProject.getId());
            }
        }
    }

    //@Scheduled(cron = "0 30 6 * * ?") // At 6:30:00 everyday
    private void checkJobDate() {
        List<Job> pendingJobList = jobRepository.findByStatus(Constant.JobStatus.PENDING);
        if(pendingJobList != null && pendingJobList.size() != 0){
           long currentTime = System.currentTimeMillis();
           for(Job pendingJob: pendingJobList){
               long jobStartDate = pendingJob.getContractByContractId().getStartDate();
               if(currentTime >= jobStartDate){
                   pendingJob.setStatus(Constant.JobStatus.ON_GOING);
                   pendingJob = jobRepository.save(pendingJob);
                   logger.log(Level.INFO, "Job (id:"+pendingJob.getId() +") has changed status from PENDING to ON_GOING.");
               }
           }

        }

        List<Job> ongoingJobList = jobRepository.findByStatus(Constant.JobStatus.ON_GOING);
        if(ongoingJobList != null && ongoingJobList.size() !=0 ){
            long currentTime = System.currentTimeMillis();
            for(Job ongoingJob: ongoingJobList){
                long jobEndDate = ongoingJob.getContractByContractId().getEndDate();
                if(currentTime >= jobEndDate){
                    ongoingJob.setStatus(Constant.JobStatus.FINISHED);
                    ongoingJob = jobRepository.save(ongoingJob);
                    logger.log(Level.INFO, "Job (id:"+ongoingJob.getId() +") has FINISHED.");
                }
            }
        }
    }

}
