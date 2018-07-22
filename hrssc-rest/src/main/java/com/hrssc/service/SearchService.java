package com.hrssc.service;

import com.hrssc.domain.SearchModel;
import com.hrssc.entities.HumanResource;
import com.hrssc.entities.Project;

import java.util.List;

public interface SearchService {

    List<HumanResource> searchResource(SearchModel searchData);
    List<Project> searchProject(SearchModel searchData);
}
