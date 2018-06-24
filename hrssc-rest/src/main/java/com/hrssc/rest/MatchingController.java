package com.hrssc.rest;

import com.hrssc.entities.HumanResource;
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

    @PostMapping(value = "/resource")
    public List<HumanResource> matchResource(@RequestBody Project project) {
        return matchingService.matchResource(project);
    }


    @PostMapping(value = "/project")
    public List<Project> matchProject(@RequestBody HumanResource resource) {
        return matchingService.matchProject(resource);
    }

}
