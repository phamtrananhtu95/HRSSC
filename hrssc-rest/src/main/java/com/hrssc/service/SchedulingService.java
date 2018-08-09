package com.hrssc.service;

import com.hrssc.domain.Constant;
import com.hrssc.entities.Project;
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
    ProjectManagementService projectManagementService;

    Logger logger = Logger.getLogger(SchedulingService.class.getName());
    //@Scheduled(fixedRate = 1000*60*60*24)
    //@Scheduled(cron = "0 30 6 * * ?)
    private void updateProjectStatus() throws Exception{
        List<Project> pendingProjectList = projectRepository.findByProcessStatusNot(Constant.ProjectProcess.FINISHED);
        if(pendingProjectList != null && pendingProjectList.size() == 0){
            return;
        }
        for(Project pendingProject: pendingProjectList){
            long currentTime = System.currentTimeMillis();
            long projectEndTime = pendingProject.getEndDate();
            if(currentTime > projectEndTime){

                projectManagementService.closeFinishedProject(pendingProject.getId());
                logger.log(Level.INFO, "Successfully updated project to FINISHED. Project ID:" +pendingProject.getId());
            }
        }
    }

    @Scheduled(cron = "0 30 6 * * ?") // At 6:30:00 everyday
    private void checkJobDate(){

    }

}
