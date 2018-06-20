package com.hrssc.rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.hrssc.domain.jacksonview.ProjectView;
import com.hrssc.entities.Project;
import com.hrssc.service.ProjectManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/manage-project")
public class ProjectManagementController {

    @Autowired
    ProjectManagementService projectManagementService;


    @JsonView(ProjectView.ListView.class)
    @GetMapping(value = "/load-project/{managerId}")
    public List<Project> getProjectByManagerId(@PathVariable(value = "managerId") int mangerId){
        return projectManagementService.getProjectByManagerId(mangerId);
    }
}
