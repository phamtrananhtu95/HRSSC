package com.hrssc.service;

import com.hrssc.entities.Project;
import com.hrssc.entities.SkillRequirements;

import java.util.List;

public interface ProjectManagementService {

    List<Project> getInvitableProjectByManagerId(int resourceId, int userId);
    List<Project> getProjectByManagerId(int managerId);

    String addProject(Project project);
    void saveProject(Project project);
    void saveSkillRequirements(SkillRequirements skillRequirements);


    String updateProject(Project project);
    String updateStatus(Project project);
    Project getProjectById(int id);
    Project viewProjectDetails(int id);
    boolean isProjectFull(int projectId);
    String closeProject(int projectId);
}
