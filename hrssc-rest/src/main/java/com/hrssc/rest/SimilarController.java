package com.hrssc.rest;


import com.fasterxml.jackson.annotation.JsonView;
import com.hrssc.domain.dto.ResponseStatus;
import com.hrssc.domain.jacksonview.SimilarView;
import com.hrssc.entities.SimilarProject;
import com.hrssc.entities.SimilarResource;
import com.hrssc.service.SimilarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/similar")
public class SimilarController {

    @Autowired
    SimilarService similarService;

    @JsonView(SimilarView.Resource.class)
    @GetMapping(value = "/get-resource-list/{resourceId}")
    public List<SimilarResource> getSimilarResource(@PathVariable(value = "resourceId") int resourceId){
          return similarService.getSimilarResourceList(resourceId);
    }

    @JsonView(SimilarView.Project.class)
    @GetMapping(value = "/get-project-list/{projectId}")
    public List<SimilarProject> getSimilarProject(@PathVariable(value = "projectId") int projectId){
        return similarService.getSimilarProjectList(projectId);
    }

    @GetMapping(value = "/test-resource/{resourceId}")
    public ResponseStatus testSimilarResource(@PathVariable(value = "resourceId") int resourceId){
        return new ResponseStatus(similarService.findSimilarResource(resourceId));

    }

    @GetMapping(value = "/test-project/{projectId}")
    public ResponseStatus testSimilarProject(@PathVariable(value = "projectId") int projectId){
        return new ResponseStatus(similarService.findSimilarProject(projectId));

    }
}
