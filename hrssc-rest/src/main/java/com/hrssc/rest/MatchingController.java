package com.hrssc.rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.hrssc.domain.jacksonview.MatchingView;
import com.hrssc.entities.HumanResource;
import com.hrssc.entities.Interaction;
import com.hrssc.entities.Project;
import com.hrssc.service.MatchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/matching")
public class MatchingController {

    @Autowired
    MatchingService matchingService;

    @JsonView(MatchingView.Project.class)
    @PostMapping(value = "/project")
    public List<Interaction> matchProject(@RequestBody Project project) {
        return matchingService.matchProject(project.getId());
    }

    @JsonView(MatchingView.Resource.class)
    @PostMapping(value = "/resource")
    public List<Interaction> matchResource(@RequestBody HumanResource resource) {
        return matchingService.matchResource(resource.getId());
    }

}
