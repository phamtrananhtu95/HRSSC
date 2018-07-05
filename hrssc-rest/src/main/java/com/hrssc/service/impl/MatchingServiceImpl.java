package com.hrssc.service.impl;

import com.fasterxml.jackson.annotation.JsonView;
import com.hrssc.domain.Constant;
import com.hrssc.domain.jacksonview.MatchingView;
import com.hrssc.domain.ranking.ScoreRanker;
import com.hrssc.entities.*;
import com.hrssc.repository.HumanResourceRepository;
import com.hrssc.repository.InteractionRepository;
import com.hrssc.repository.ProjectRepository;
import com.hrssc.repository.UserRepository;
import com.hrssc.service.MatchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.*;

@Service("matchingService")
public class MatchingServiceImpl implements MatchingService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    HumanResourceRepository humanResourceRepo;

    @Autowired
    InteractionRepository interactionRepo;

    @PersistenceContext
    EntityManager entityManager;

    //Sort Ascending
    private Comparator<Interaction> comparator = new Comparator<Interaction>() {
        @Override
        public int compare(Interaction o1, Interaction o2) {
            if (o1.getRankingScore() < o2.getRankingScore()) {
                return 1;
            }
            if (o1.getRankingScore() > o2.getRankingScore()) {
                return -1;
            }
            return 0;
        }
    };

    @Transactional
    @Override
    public List<Interaction> matchProject(int projectId) {
        entityManager.clear();
        Project project = projectRepository.findById(projectId);
        //Find all the available resource that is not in the same company
        if (project == null) {
            return null;
        }
        List<Interaction> resultList = null;
        List<HumanResource> resourceList =
                humanResourceRepo.findByStatusAndCompanyIdNot(Constant.ResourceStatus.AVAILABLE, project.getCompanyId());
        interactionRepo.deleteByProjectIdAndType(projectId,Constant.InteractionType.MATCH);
        if (resourceList != null) {
            ScoreRanker sr = new ScoreRanker(5.0, 2.0, 2.0, 0.7, 0.3);
            List<Interaction> matchedList = new ArrayList<>();
            resultList = new ArrayList<>();
            for (HumanResource resource : resourceList) {
                double rankingScore = sr.rankingScore(resource, project);
                if (rankingScore > 0) {
                    Interaction matched = new Interaction(Constant.InteractionType.MATCH);
                    matched.setHumanResourceId(resource.getId());
                    matched.setProjectId(project.getId());
                    matched.setRankingScore(rankingScore);
                    matched.setHumanResourceByHumanResourceId(resource);
                    matchedList.add(matched);
                }

            }
            matchedList.sort(comparator);
            for (int i = 0; ; i++) {
                if (i >= 10 || i >= matchedList.size()) {
                    break;
                }
                Interaction sortedMatch = matchedList.get(i);
                sortedMatch = interactionRepo.save(sortedMatch);
                resultList.add(sortedMatch);
            }
        }
        //Sort in case resourceList is null
        resultList.sort(comparator);
        return resultList;
    }


    @Transactional
    @Override
    public List<Interaction> matchResource(int resourceId) {
        entityManager.clear();
        HumanResource resource = humanResourceRepo.getById(resourceId);
        //HumanResource resource = resourceOptional.get();

        //Truyền sai resourceId
        if (resource == null) {
            return null;
        }

        //Không match resource Inactive
        if(resource.getStatus()== Constant.ResourceStatus.INACTIVE){
            return null;
        }

        //Không match resource Busy
        if(resource.getStatus()== Constant.ResourceStatus.BUSY){
            return null;
        }

        List<Interaction> resultList = null;
        //Find all the project that is not int the same company
        List<Project> projectList = projectRepository.findByRequestStatusAndCompanyIdNot(
                Constant.RequestStatus.OPENNING, resource.getCompanyId());
        interactionRepo.deleteByHumanResourceIdAndType(resourceId,Constant.InteractionType.MATCH);
        if (projectList != null) {
            ScoreRanker sr = new ScoreRanker(5.0, 2.0, 2.0, 0.7, 0.3);
            List<Interaction> matchedList = new ArrayList<>();
            resultList = new ArrayList<>();
            for (Project project : projectList) {
                double rankingScore = sr.rankingScore(resource, project);
                if (rankingScore > 0) {
                    Interaction matched = new Interaction(Constant.InteractionType.MATCH);
                    matched.setHumanResourceId(resource.getId());
                    matched.setProjectId(project.getId());
                    matched.setRankingScore(rankingScore);
                    matched.setProjectByProjectId(project);
                    matchedList.add(matched);
                }
            }
            matchedList.sort(comparator);
            for (int i = 0; ; i++) {
                if (i >= 5 || i >= matchedList.size()) {
                    break;
                }
                Interaction sortedMatch = matchedList.get(i);
                sortedMatch = interactionRepo.save(sortedMatch);
                resultList.add(sortedMatch);
            }
        }
        //Sort in case resourceList is null
        resultList.sort(comparator);
        return resultList;
    }

    @Override
    public List<Interaction> getMatchedResourceListByProjectId(int projectId,int userId){


       List<Interaction> resourceList = interactionRepo.findByProjectIdAndType(projectId,Constant.InteractionType.MATCH);
       resourceList.sort(comparator);
       return  resourceList;
    }

    @Override
    public List<Interaction> getMatchedProjectListByResourceId(int resourceId,int userId){
        List<Interaction> projectList = interactionRepo.findByHumanResourceIdAndType(resourceId, Constant.InteractionType.MATCH);
        projectList.sort(comparator);
        return  projectList;
    }
}
