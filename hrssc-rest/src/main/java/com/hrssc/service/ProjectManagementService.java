package com.hrssc.service;

import com.hrssc.entities.Project;

import java.util.List;

public interface ProjectManagementService {


    List<Project> getProjectByManagerId(int managerId);
}
