package com.hrssc.service.impl;

import com.hrssc.domain.Constant;
import com.hrssc.domain.ranking.ScoreRanker;
import com.hrssc.entities.*;
import com.hrssc.repository.HumanResourceRepository;
import com.hrssc.repository.ProjectRepository;
import com.hrssc.service.MatchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("matchingService")
public class MatchingServiceImpl implements MatchingService {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    HumanResourceRepository humanResourceRepo;


    //Sort Ascending
    private Comparator<Interaction> comparator = new Comparator<Interaction>() {
        @Override
        public int compare(Interaction o1, Interaction o2) {
            if(o1.getRankingScore() < o2.getRankingScore()){
                return 1;
            }
            if(o1.getRankingScore() > o2.getRankingScore()){
                return -1;
            }
            return 0;
        }
    };

    @Override
    public List<HumanResource> matchResource(int projectId) {
        Project project = projectRepository.findById(projectId);
        //Find all the available resource that is not in the same company
        List<HumanResource> resourceList =
                humanResourceRepo.findByStatusAndCompanyIdNot(Constant.ResourceStatus.AVAILABLE,project.getCompanyId());
        if(resourceList != null){
            ScoreRanker sr = new ScoreRanker(5.0,2.0,2.0,0.7,0.3);
            List<Interaction> matchedList =  new ArrayList<>();
            for(HumanResource resource: resourceList){
                double rankingScore = sr.rankingScore(resource,project);
                if(rankingScore > 0){
                    Interaction matched = new Interaction(Constant.InteractionType.MATCH);
                    matched.setHumanResourceId(resource.getId());
                    matched.setProjectId(project.getId());
                    matched.setRankingScore(rankingScore);
                    matched.setHumanResourceByHumanResourceId(resource);
                    matchedList.add(matched);
                }

            }

            matchedList.sort(comparator);
            resourceList = new LinkedList<>();
            for(Interaction sortedMatch: matchedList){
                //Insert to DB here
                resourceList.add(sortedMatch.getHumanResourceByHumanResourceId());
            }
        }
        return resourceList;
    }

    @Override
    public List<Project> matchProject(int resourceId) {
        Optional<HumanResource> resourceOptional = humanResourceRepo.findById(resourceId);
        HumanResource resource = resourceOptional.get();
        //Find all the project that is not int the same company
        List<Project> projectList = projectRepository.findByRequestStatusAndCompanyIdNot(
                Constant.RequestStatus.OPENNING,resource.getCompanyId());
        if(projectList != null){
            ScoreRanker sr = new ScoreRanker(5.0,2.0,2.0,0.7,0.3);
            List<Interaction> matchedList =  new ArrayList<>();
            for(Project project: projectList){
                double rankingScore = sr.rankingScore(resource,project);
                Interaction matched = new Interaction(Constant.InteractionType.MATCH);
                matched.setHumanResourceId(resource.getId());
                matched.setProjectId(project.getId());
                matched.setRankingScore(rankingScore);
                matched.setProjectByProjectId(project);
                matchedList.add(matched);

            }
            matchedList.sort(comparator);
            projectList = new LinkedList<>();
            for(Interaction sortedMatch: matchedList){
                //Insert DB here
                projectList.add(sortedMatch.getProjectByProjectId());
            }
        }
        return projectList;
    }


}
