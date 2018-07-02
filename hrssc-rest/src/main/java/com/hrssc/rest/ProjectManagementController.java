package com.hrssc.rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.hrssc.domain.dto.ResponseStatus;
import com.hrssc.domain.jacksonview.ProjectView;
import com.hrssc.entities.Project;
import com.hrssc.service.AuthorizationService;
import com.hrssc.service.ProjectManagementService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manage-project")
public class ProjectManagementController {

    @Autowired
    ProjectManagementService projectManagementService;

    @Autowired
    AuthorizationService authorizationService;

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

    @JsonView(ProjectView.Summary.class)
    @GetMapping(value = "get-summary/{projectId}")
    public Project getProjectSummaryById(@PathVariable(value = "projectId") int id){
        return projectManagementService.getProjectById(id);
    }

    @JsonView(ProjectView.details.class)
    @GetMapping(value = "/details/{userId}/{projectId}")
    public Project viewProjectDetails(@PathVariable(value = "projectId") int projectId,
                                      @PathVariable(value = "userId" )int userId) {
        if(!authorizationService.checkProject(projectId,userId)){
            return null;
        }
        return projectManagementService.viewProjectDetails(projectId);
    }
}
