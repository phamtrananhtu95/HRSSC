package com.hrssc.service.impl;

import com.hrssc.domain.Constant;
import com.hrssc.domain.jacksonview.MatchingView;
import com.hrssc.domain.ranking.ScoreRanker;
import com.hrssc.entities.HumanResource;
import com.hrssc.entities.Project;
import com.hrssc.entities.SimilarProject;
import com.hrssc.entities.SimilarResource;
import com.hrssc.repository.HumanResourceRepository;
import com.hrssc.repository.ProjectRepository;
import com.hrssc.repository.SimilarProjectRepository;
import com.hrssc.repository.SimilarResourceRepository;
import com.hrssc.service.SimilarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service("similarService")
public class SimilarServiceImpl implements SimilarService {

    @Autowired
    HumanResourceRepository humanResourceRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    SimilarResourceRepository similarResourceRepository;

    @Autowired
    SimilarProjectRepository similarProjectRepository;

    @PersistenceContext
    EntityManager entityManager;

    private Comparator<SimilarResource> resourceComparator = new Comparator<SimilarResource>() {
        @Override
        public int compare(SimilarResource o1, SimilarResource o2) {
            if (o1.getSimilarScore() < o2.getSimilarScore()) {
                return 1;
            }
            if (o1.getSimilarScore() > o2.getSimilarScore()) {
                return -1;
            }
            return 0;
        }
    };
    private Comparator<SimilarProject> projectComparator = new Comparator<SimilarProject>() {
        @Override
        public int compare(SimilarProject o1, SimilarProject o2) {
            if (o1.getSimilarScore() < o2.getSimilarScore()) {
                return 1;
            }
            if (o1.getSimilarScore() > o2.getSimilarScore()) {
                return -1;
            }
            return 0;
        }
    };

    @Transactional
    @Override
    public String findSimilarResource(int resourceId){
        entityManager.clear();
        HumanResource resource = humanResourceRepository.getById(resourceId);
        if(resource == null){
            return "Resource not existed.";
        }
        List<HumanResource> matchingList = humanResourceRepository.findByStatusAndCompanyIdNot(Constant.ResourceStatus.AVAILABLE,resource.getCompanyId());
        similarResourceRepository.deleteByHumanResourceId(resourceId);
        if(matchingList != null){
            List<SimilarResource> similarList = new ArrayList<>();
            ScoreRanker sr = new ScoreRanker(5.0,1.0,1.0,0.7,0.3);
            for(HumanResource matchResource: matchingList){
                double similarScore = sr.calculateResourceSimilarScore(resource,matchResource);
                if(similarScore > 0){
                    SimilarResource similar = new SimilarResource();
                    similar.setHumanResourceId(resourceId);
                    similar.setSimilarResourceId(matchResource.getId());
                    similar.setSimilarScore(similarScore);
                    similarList.add(similar);
                }
            }
            if(similarList.size() > 0){
                similarList.sort(resourceComparator);
                for (int i = 0; i < 5; i++) {
                    if (i == similarList.size()) {
                        break;
                    }
                    similarResourceRepository.save(similarList.get(i));
                }
            }
        }
        return "Resource Similarity Done.";
    }

    @Transactional
    @Override
    public String findSimilarProject(int projectId){
        entityManager.clear();
        Project project = projectRepository.findById(projectId);
        if(project == null){
            return "Project not existed.";
        }
        List<Project> matchingList = projectRepository.findByRequestStatusAndCompanyIdNot(Constant.RequestStatus.OPENNING,project.getCompanyId());
        similarProjectRepository.deleteByProjectId(projectId);
        if(matchingList != null){
            List<SimilarProject> similarList = new ArrayList<>();
            ScoreRanker sr = new ScoreRanker(5.0,1.0,1.0,0.7,0.3);
            for(Project matchProject: matchingList){
                double similarScore = sr.calculateProjectSimilarScore(project,matchProject);
                if(similarScore > 0){
                    SimilarProject similar = new SimilarProject();
                    similar.setProjectId(projectId);
                    similar.setSimilarProjectId(matchProject.getId());
                    similar.setSimilarScore(similarScore);
                    similarList.add(similar);
                }
            }
            if(similarList.size() > 0){
                similarList.sort(projectComparator);
                for (int i = 0; i < 5; i++) {
                    if (i == similarList.size()) {
                        break;
                    }
                    similarProjectRepository.save(similarList.get(i));
                }
            }
        }
        return "Project Similarity Done.";
    }

    public List<SimilarProject> getSimilarProjectList(int projectId){
        List<SimilarProject> projectList = similarProjectRepository.findByProjectId(projectId);
        List<SimilarProject> resultList = new ArrayList<>();
        if(projectList != null){
            projectList.sort(projectComparator);
            for(int i = 0; i < projectList.size();i++){
                if(i == 5){
                    break;
                }
                resultList.add(projectList.get(i));
            }

        }
        return resultList;
    }

    public List<SimilarResource> getSimilarResourceList(int resourceId){
        List<SimilarResource> resourceList = similarResourceRepository.findByHumanResourceId(resourceId);
        List<SimilarResource> resultList = new ArrayList<>();
        if(resourceList != null){
            resourceList.sort(resourceComparator);
            for(int i = 0; i < resourceList.size();i++){
                if(i == 5){
                    break;
                }
                resultList.add(resourceList.get(i));
            }

        }
        return resultList;
    }
}
