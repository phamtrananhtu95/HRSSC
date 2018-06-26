package com.hrssc.service.impl;

import com.hrssc.domain.Constant;
import com.hrssc.domain.ranking.ScoreRanker;
import com.hrssc.entities.*;
import com.hrssc.repository.HumanResourceRepository;
import com.hrssc.repository.ProjectRepository;
import com.hrssc.service.MatchingService;
import org.apache.tomcat.util.bcel.Const;
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
        //Find all the available resource that is not in the same company
        List<HumanResource> resourceList =
                humanResourceRepo.findByStatusAndCompanyIdNot(Constant.ResourceStatus.ACTIVATED,project.getCompanyId());
        if(resourceList != null){
            ScoreRanker sr = new ScoreRanker(5.0,2.0,2.0,0.7,0.3);
            for(HumanResource resource: resourceList){
                double rankingScore = sr.rankingScore(resource,project);
                if(rankingScore > 0){
                    Interaction matched = new Interaction(Constant.InteractionType.MATCH);
                    matched.setHumanResourceId(resource.getId());

                }

            }
        }



        return null;
    }

    @Override
    public List<Project> matchProject(HumanResource resource) {
        //Find all the project that is not int the same company
        List<Project> projectList = projectRepository.findAllByCompanyIdNot(resource.getCompanyId());


        return null;
    }


}
