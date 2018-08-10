package com.hrssc.service;

import com.hrssc.entities.Job;
import com.hrssc.entities.Project;
import com.hrssc.entities.SkillRequirements;

import java.util.List;

public interface ProjectManagementService {

    List<Project> getInvitableProjectByManagerId(int resourceId, int userId);
    List<Project> getProjectByManagerId(int managerId);
    List<Job> getJoinedResource(int projectId);
    List<Project> getHomeProjectList(int userId);
    Project addProject(Project project);
    void saveProject(Project project);



    Project closeFinishedProject(int projectId) throws Exception;
    String updateProject(Project project);
    String updateStatus(Project project);
    Project getProjectById(int id);
    Project viewProjectDetails(int id);
    boolean isProjectFull(int projectId);
    String closeProject(int projectId);
    String releaseResource(int jobId);
    String rejectResource(int jobId);
    String removedProject(int projectId);
}
