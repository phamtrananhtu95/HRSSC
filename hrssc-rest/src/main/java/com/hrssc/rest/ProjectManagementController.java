package com.hrssc.rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.hrssc.domain.Constant;
import com.hrssc.domain.dto.ResponseStatus;
import com.hrssc.domain.jacksonview.FeedbackView;
import com.hrssc.domain.jacksonview.JobView;
import com.hrssc.domain.jacksonview.ProjectView;
import com.hrssc.entities.Interaction;
import com.hrssc.entities.Job;
import com.hrssc.entities.Project;
import com.hrssc.service.AuthorizationService;
import com.hrssc.service.MatchingService;
import com.hrssc.service.ProjectManagementService;
import com.hrssc.service.SimilarService;
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

    @Autowired
    MatchingService matchingService;

    @Autowired
    SimilarService similarService;

    @JsonView(ProjectView.ListView.class)
    @GetMapping(value = "/load-project/{managerId}")
    public List<Project> getProjectByManagerId(@PathVariable(value = "managerId") int mangerId){
        return projectManagementService.getProjectByManagerId(mangerId);
    }

    @JsonView(ProjectView.ListView.class)
    @GetMapping(value = "/get-invitable-project/{userId}/{resourceId}")
    public List<Project> getInvitableProjectByManager(@PathVariable(value = "resourceId") int resourceId,
                                                      @PathVariable(value = "userId") int userId){
        return projectManagementService.getInvitableProjectByManagerId(resourceId,userId);
    }

    @JsonView(JobView.JoinedResource.class)
    @GetMapping(value = "/get-joined-resource/{projectId}")
    public List<Job> getJoinedList(@PathVariable(value = "projectId") int projectId){
        return projectManagementService.getJoinedResource(projectId);
    }


    @PostMapping(value = "/add")
    public ResponseStatus addProject(@RequestBody Project project){
        ResponseStatus response = new ResponseStatus(projectManagementService.addProject(project));
        matchingService.matchProject(project.getId());
        similarService.findSimilarProject(project.getId());
       return response;
    }

    @PostMapping(value = "/update")
    public ResponseStatus updateProject(@RequestBody Project project){
        ResponseStatus response = new ResponseStatus(projectManagementService.updateProject(project));
        matchingService.matchProject(project.getId());
        similarService.findSimilarProject(project.getId());
        return response;
    }

    @PostMapping(value="/change-status/")
    public ResponseStatus updateProjectStatus(@RequestBody Project project){
        ResponseStatus response = new ResponseStatus(projectManagementService.updateStatus(project));
        if(project.getRequestStatus() == Constant.RequestStatus.OPENNING){
            matchingService.matchProject(project.getId());
        }
        return response;
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

    @JsonView(FeedbackView.projectFeedback.class)
    @GetMapping(value = "/change-status-finish/{projectId}")
    public Project closeFinishedProject(@PathVariable(value = "projectId") int projectId) throws Exception{
        return projectManagementService.closeFinishedProject(projectId);
    }
}
