package com.hrssc.rest;

import com.fasterxml.jackson.annotation.JsonView;
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
    public List<Interaction> matchProject(@RequestBody Project project) {
        return matchingService.matchProject(project.getId());
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
    public List<Interaction> matchResource(@RequestBody HumanResource resource) {
        return matchingService.matchResource(resource.getId());
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

    @GetMapping(value = "/similar-resource/{resourceId}")
    public List<Interaction> findSimilarResource(@PathVariable(value = "resourceId") int resourceId){
        return null;
    }

}
