package com.hrssc.service;

import com.hrssc.entities.HumanResource;
import com.hrssc.entities.Interaction;
import com.hrssc.entities.Project;

import java.util.List;

public interface MatchingService {

    List<Interaction> matchProject(int projectId);
    List<Interaction> matchResource(int resourceId);
    List<Interaction> getMatchedResourceListByProjectId(int projectId);
    List<Interaction> getMatchedProjectListByResourceId(int resourceId);
}
