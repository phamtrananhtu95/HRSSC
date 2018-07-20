package com.hrssc.rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.hrssc.domain.SearchModel;
import com.hrssc.domain.jacksonview.HomeView;
import com.hrssc.entities.HumanResource;
import com.hrssc.entities.Project;
import com.hrssc.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    SearchService searchService;

    @JsonView(HomeView.Resource.class)
    @PostMapping(value = "/by-resource")
    public List<HumanResource> searchByResource(@RequestBody SearchModel searchData){
        return searchService.searchResource(searchData);
    }

    @JsonView(HomeView.Project.class)
    @PostMapping(value = "/by-project")
    public List<Project> searchByProject(@RequestBody SearchModel searchData){
        return searchService.searchProject(searchData);
    }
}
