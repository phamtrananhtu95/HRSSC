package com.hrssc.service.impl;

import com.hrssc.entities.PositionRequirements;
import com.hrssc.entities.Project;
import com.hrssc.entities.SkillRequirements;
import com.hrssc.repository.PositionRequirementsRepository;
import com.hrssc.repository.ProjectRepository;
import com.hrssc.repository.SkillRequirementsRepository;
import com.hrssc.service.ProjectManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    public String addProject(Project project) {
        try {
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

            prj = projectRepository.findByTitleAndUserId(project.getTitle(), project.getUserId());

            for (SkillRequirements skRequirement : project.getSkillRequirementsById()) {
                skRequirement.setProjectId(prj.getId());
                saveSkillRequirements(skRequirement);

            }
            for (PositionRequirements posRequirement : project.getPositionRequirementsById()) {
                posRequirement.setProjectId(prj.getId());
                savePositionRequirements(posRequirement);
            }
            return "Successfully Added a Project.";
        }catch (IncorrectResultSizeDataAccessException e){
            Logger.getLogger(ProjectManagementServiceImpl.class.getName()).log(Level.INFO,e.toString());
            return "Project has existed, add project failed.";
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
