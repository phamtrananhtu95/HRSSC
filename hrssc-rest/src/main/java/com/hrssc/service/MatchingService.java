package com.hrssc.service;

import com.hrssc.entities.HumanResource;
import com.hrssc.entities.Project;

import java.util.List;

public interface MatchingService {

    public List<HumanResource> matchResource(int projectId);
    public List<Project> matchProject(int resourceId);
}
