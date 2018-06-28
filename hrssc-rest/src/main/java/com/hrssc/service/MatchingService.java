package com.hrssc.service;

import com.hrssc.entities.HumanResource;
import com.hrssc.entities.Interaction;
import com.hrssc.entities.Project;

import java.util.List;

public interface MatchingService {

    public List<Interaction> matchProject(int projectId);
    public List<Interaction> matchResource(int resourceId);
}
