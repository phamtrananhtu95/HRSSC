package com.hrssc.service.impl;

import com.hrssc.entities.Project;
import com.hrssc.repository.ProjectRepository;
import com.hrssc.service.ProjectManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("projectManagement")
public class ProjectManagementServiceImpl implements ProjectManagementService {

    @Autowired
    ProjectRepository projectRepository;

    @Override
    public List<Project> getProjectByManagerId(int managerId) {
        return projectRepository.getProjectByUserId(managerId);
    }
}
