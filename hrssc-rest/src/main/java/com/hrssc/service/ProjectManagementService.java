package com.hrssc.service;

import com.hrssc.entities.Project;
import com.hrssc.entities.SkillRequirements;

import java.util.List;

public interface ProjectManagementService {


    List<Project> getProjectByManagerId(int managerId);

    String addProject(Project project);
    void saveProject(Project project);
    void saveSkillRequirements(SkillRequirements skillRequirements);


    String updateProject(Project project);
}
