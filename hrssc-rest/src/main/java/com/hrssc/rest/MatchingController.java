package com.hrssc.rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.hrssc.domain.dto.ResponseStatus;
import com.hrssc.domain.jacksonview.MatchingView;
import com.hrssc.entities.HumanResource;
import com.hrssc.entities.Interaction;
import com.hrssc.entities.Project;
import com.hrssc.service.AuthorizationService;
import com.hrssc.service.MatchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/matching")
public class MatchingController {

    @Autowired
    MatchingService matchingService;

    @Autowired
    AuthorizationService authorizationService;

    @JsonView(MatchingView.Project.class)
    @PostMapping(value = "/project")
    public ResponseStatus matchProject(@RequestBody Project project) {
        ResponseStatus response = new ResponseStatus();
        response.setCode("500");
        response.setMessage("Error occurred, Matching project failed");
        if(matchingService.matchProject(project.getId()) != null){
            response.setCode("200");
            response.setMessage("Successfully Matched project");
        }
        return response;
    }

    @JsonView(MatchingView.Resource.class)
    @GetMapping(value = "/get-matched-project/{userId}/{resourceId}")
    public List<Interaction> getMatchedProjectList(@PathVariable(value = "resourceId") int resourceId,
                                                   @PathVariable(value = "userId") int userId){
        if(!authorizationService.checkResource(resourceId,userId)){
            return null;
        }
        return matchingService.getMatchedProjectListByResourceId(resourceId,userId);
    }

    @JsonView(MatchingView.Resource.class)
    @PostMapping(value = "/resource")
    public ResponseStatus matchResource(@RequestBody HumanResource resource) {
        ResponseStatus response = new ResponseStatus();
        response.setCode("500");
        response.setMessage("Error occurred, Matching resource failed");
        if(matchingService.matchResource(resource.getId()) != null){
            response.setCode("200");
            response.setMessage("Successfully Matched resource");
        }
        return response;
    }

    @JsonView(MatchingView.Project.class)
    @GetMapping(value = "/get-matched-resource/{userId}/{projectId}")
    public List<Interaction> getMatchedResourceList(@PathVariable(value = "projectId") int projectId,
                                                    @PathVariable(value = "userId") int userId){
        if(!authorizationService.checkProject(projectId,userId)){
            return null;
        }
        return matchingService.getMatchedResourceListByProjectId(projectId,userId);
    }



}
