package com.hrssc.service;

import com.hrssc.entities.SimilarProject;
import com.hrssc.entities.SimilarResource;

import java.util.List;

public interface SimilarService {
    String findSimilarResource(int resourceId);
    String findSimilarProject(int projectId);
    List<SimilarProject> getSimilarProjectList(int projectId);
    List<SimilarResource> getSimilarResourceList(int resourceId);
}
