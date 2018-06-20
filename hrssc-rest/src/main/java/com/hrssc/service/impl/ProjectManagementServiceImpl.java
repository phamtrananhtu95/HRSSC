package com.hrssc.service.impl;

import com.hrssc.entities.PositionRequirements;
import com.hrssc.entities.Project;
import com.hrssc.entities.SkillRequirements;
import com.hrssc.repository.PositionRequirementsRepository;
import com.hrssc.repository.ProjectRepository;
import com.hrssc.repository.SkillRequirementsRepository;
import com.hrssc.service.ProjectManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("projectManagement")
public class ProjectManagementServiceImpl implements ProjectManagementService {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    SkillRequirementsRepository skillRequirementsRepository;

    @Autowired
    PositionRequirementsRepository positionRequirementsRepository;

    @Override
    public List<Project> getProjectByManagerId(int managerId) {
        return projectRepository.getProjectByUserId(managerId);
    }

    @Transactional
    @Override
    public void addProject(Project project) {
        Project prj = new Project();
        prj.setTitle(project.getTitle());
        prj.setDescription(project.getDescription());
        prj.setCreateDate(project.getCreateDate());
        prj.setEndDate(project.getEndDate());
        prj.setDuration(project.getDuration());
        prj.setType(project.getType());
        prj.setDomain(project.getDomain());
        prj.setProcessStatus(project.getProcessStatus());
        prj.setRequestStatus(project.getRequestStatus());
        prj.setUserId(project.getUserId());
        prj.setCompanyId(project.getCompanyId());
        prj.setPayment(project.getPayment());
        saveProject(prj);

        prj = projectRepository.findByTitleAndUserId(project.getTitle(),project.getUserId());

        for(SkillRequirements skRequirement: project.getSkillRequirementsById()){
            SkillRequirements skr = new SkillRequirements();
            skr.setProjectId(prj.getId());
            skr.setSkillId(skRequirement.getSkillId());
            skr.setExperience(skRequirement.getExperience());
            saveSkillRequirements(skr);
        }
        for(PositionRequirements posRequirement: project.getPositionRequirementsById()){
            PositionRequirements pr = new PositionRequirements();
            pr.setProjectId(prj.getId());
            pr.setPositionId(posRequirement.getPositionId());
            savePositionRequirements(pr);
        }
    }
    @Override
    public void saveProject(Project project){
        projectRepository.save(project);
    }
    @Override
    public void saveSkillRequirements(SkillRequirements skillRequirements){
        skillRequirementsRepository.save(skillRequirements);
    }
    @Override
    public void savePositionRequirements(PositionRequirements positionRequirements){
        positionRequirementsRepository.save(positionRequirements);
    }
}
