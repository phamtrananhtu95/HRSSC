package com.hrssc.rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.hrssc.domain.dto.ResponseStatus;
import com.hrssc.domain.jacksonview.ProjectView;
import com.hrssc.entities.Project;
import com.hrssc.service.ProjectManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping(value = "/add")
    public ResponseStatus addProject(@RequestBody Project project){
       return new ResponseStatus(projectManagementService.addProject(project));
    }

    @PostMapping(value = "/update")
    public ResponseStatus updateProject(@RequestBody Project project){
        return new ResponseStatus(projectManagementService.updateProject(project));
    }

    @PostMapping(value="/change-status/")
    public ResponseStatus updateProjectStatus(@RequestBody Project project){
        return new ResponseStatus(projectManagementService.updateStatus(project));
    }
}
