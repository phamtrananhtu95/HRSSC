package com.hrssc.service;

import com.hrssc.entities.HumanResource;
import com.hrssc.entities.Project;

import java.util.List;

public interface MatchingService {

    public List<HumanResource> matchResource(Project project);
    public List<Project> matchProject(HumanResource resource);
}
