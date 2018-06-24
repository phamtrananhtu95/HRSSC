package com.hrssc.service.impl;

import com.hrssc.entities.*;
import com.hrssc.repository.HumanResourceRepository;
import com.hrssc.repository.ProjectRepository;
import com.hrssc.service.MatchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("matchingService")
public class MatchingServiceImpl implements MatchingService {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    HumanResourceRepository humanResourceRepo;

    @Override
    public List<HumanResource> matchResource(Project project) {
        //Find all the resource that is not int the same company
        List<HumanResource> resourceList = humanResourceRepo.findByCompanyByCompanyIdNot(project.getCompanyId());

        return null;
    }

    @Override
    public List<Project> matchProject(HumanResource resource) {
        //Find all the project that is not int the same company
        List<Project> projectList = projectRepository.findAllByCompanyIdNot(resource.getCompanyId());


        return null;
    }


}
