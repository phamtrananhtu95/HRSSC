package com.hrssc.service.impl;

import com.hrssc.domain.Constant;
import com.hrssc.entities.Project;
import com.hrssc.entities.ProjectRequirements;
import com.hrssc.entities.SkillRequirements;
import com.hrssc.repository.InteractionRepository;
import com.hrssc.repository.ProjectRepository;
import com.hrssc.repository.ProjectRequirementRepository;
import com.hrssc.repository.SkillRequirementsRepository;
import com.hrssc.service.ProjectManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    ProjectRequirementRepository projectRequirementRepository;

    @Autowired
    InteractionRepository interactionRepository;


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
            prj.setRequestStatus(Constant.RequestStatus.OPENNING);
            prj.setUserId(project.getUserId());
            prj.setCompanyId(project.getCompanyId());
            prj = projectRepository.save(prj);
//            prj = projectRepository.findByTitleAndUserId(prj.getTitle(), prj.getUserId());


            for (ProjectRequirements prjReq : project.getProjectRequirementsById()) {
                prjReq.setProjectId(prj.getId());
                prjReq = projectRequirementRepository.save(prjReq);
                for (SkillRequirements skr : prjReq.getSkillRequirementsById()) {
                    skr.setProjectRequirementsId(prjReq.getId());
                    saveSkillRequirements(skr);
                }
            }

            return "Successfully Added a Project.";
        } catch (IncorrectResultSizeDataAccessException e) {
            Logger.getLogger(ProjectManagementServiceImpl.class.getName()).log(Level.INFO, e.toString());
            return "Project has existed, add project failed.";
        }
    }

    @Override
    public void saveProject(Project project) {
        projectRepository.save(project);
    }

    @Override
    public void saveSkillRequirements(SkillRequirements skillRequirements) {
        skillRequirementsRepository.save(skillRequirements);
    }


    @Transactional
    @Override
    public String updateProject(Project project) {
        try {
            Project prjEntity = projectRepository.findById(project.getId());
            if (prjEntity == null) {
                return "Error Occured, Update Failed.";
            }
            prjEntity.setTitle(project.getTitle());
            prjEntity.setDescription(project.getDescription());
            prjEntity.setCreateDate(project.getCreateDate());
            prjEntity.setEndDate(project.getEndDate());
            prjEntity.setDuration(project.getDuration());
            prjEntity.setType(project.getType());
            prjEntity.setDomain(project.getDomain());
            prjEntity.setProcessStatus(project.getProcessStatus());
            prjEntity.setRequestStatus(project.getRequestStatus());
            projectRepository.save(prjEntity);

            List<ProjectRequirements> requirementList = projectRequirementRepository.findByProjectId(project.getId());

            //Nếu requirementList null thì có nghĩa là Project này được tạo mà không có bất kì 1 requirement nào
            //variable "project" mang theo 1 list các requirement được gửi từ Front-end
            //Nếu dưới DB chưa có requirement nào thì chỉ cần đơn giản là add requirement đó vào

            //Với mỗi requirement được chứa trong request
            for (ProjectRequirements requirement : project.getProjectRequirementsById()) {

                //Kiểm tra xem nó có dưới DB hay chưa ?
                ProjectRequirements requirementsEntity = projectRequirementRepository.findById(requirement.getId());

                //Nếu Chưa thì tạo mới object để thực hiện như 1 insert query (JPA hỗ trợ hàm "save" cho cả update và insert)
                if (requirementsEntity == null) {
                    requirementsEntity = new ProjectRequirements();
                }
                //Set projectId này cần thiết cho trường hợp insert
                requirementsEntity.setProjectId(project.getId());
                requirementsEntity.setPayment(requirement.getPayment());
                requirementsEntity.setPositionId(requirement.getPositionId());
                requirementsEntity.setQuantity(requirement.getQuantity());
                if(requirement.getQuantity() == 0){
                    requirementsEntity.setQuantity(1);
                }else{
                    requirementsEntity.setQuantity(requirement.getQuantity());
                }
                requirementsEntity = projectRequirementRepository.save(requirementsEntity);
//                requirementsEntity = projectRequirementRepository.findByProjectIdAndPositionIdAndPayment(
//                        requirementsEntity.getProjectId(),requirementsEntity.getPositionId(),requirementsEntity.getPayment()
//                );

                        //Update Skill của requirement
                List<SkillRequirements> skillList = skillRequirementsRepository.findByProjectRequirementsId(requirementsEntity.getId());
                for (SkillRequirements skillReq : requirement.getSkillRequirementsById()) {
                    SkillRequirements skillReqEntity = skillRequirementsRepository.findById(skillReq.getId());
                    if (skillReqEntity == null) {
                        skillReqEntity = new SkillRequirements();
                    }
                    //Id Cần cho trường hợp insert
                    skillReqEntity.setProjectRequirementsId(requirementsEntity.getId());
                    skillReqEntity.setExperience(skillReq.getExperience());
                    skillReqEntity.setSkillId(skillReq.getSkillId());
                    skillRequirementsRepository.save(skillReqEntity);
                    //Xóa skill đã được update khỏi skillList.
                    for (SkillRequirements skr : skillList) {
                        if (skr.getId() == skillReqEntity.getId()) {
                            skillList.remove(skr);
                            break;
                        }
                    }
                }
                //Nếu toàn bộ skill đã được update, nhưng skillList vẫn còn skill thì có nghĩa là đó là những skill đã bị xóa trên FE nhưng vẫn còn dưới DB
                //Tiến hành xóa những skill đó khỏi DB
                for (SkillRequirements deleteSKR : skillList) {
                    skillRequirementsRepository.delete(deleteSKR);
                }


                //Xóa requirement đã được khỏi requirementList
                for (ProjectRequirements pr : requirementList) {
                    if (pr.getId() == requirementsEntity.getId()) {
                        requirementList.remove(pr);
                        break;
                    }
                }
            }

            //Nếu toàn bộ requirement đã được update, nhưng requirementList vẫn còn requirement thì có nghĩa là đó là những requirement đã bị xóa trên FE nhưng vẫn còn dưới DB
            //Tiến hành xóa những requirement đó khỏi DB
            for (ProjectRequirements deletePR : requirementList) {
                skillRequirementsRepository.deleteByProjectRequirementsId(deletePR.getId());
                projectRequirementRepository.delete(deletePR);
            }

            return "Successfully Update Project";
        } catch (RuntimeException e) {
            Logger.getLogger(ProjectManagementService.class.getName()).log(Level.INFO, e.toString());
            return "Error Occured, Update Failed.";
        }


    }

    @Transactional
    @Override
    public String updateStatus(Project project) {
        Project prjEntity = projectRepository.findById(project.getId());

        if (prjEntity != null) {
            int requestStatus = project.getRequestStatus();
            if (requestStatus == Constant.RequestStatus.REMOVED) {
                prjEntity.setRequestStatus(Constant.RequestStatus.REMOVED);
            }
            if (requestStatus == Constant.RequestStatus.OPENNING) {
                prjEntity.setRequestStatus(Constant.RequestStatus.OPENNING);
            }
            if (requestStatus == Constant.RequestStatus.CLOSED) {
                prjEntity.setRequestStatus(Constant.RequestStatus.CLOSED);
            }

            int proccessStatus = project.getProcessStatus();
            if (proccessStatus == Constant.ProjectProcess.PENDING) {
                prjEntity.setProcessStatus(Constant.ProjectProcess.PENDING);
            }
            if (proccessStatus == Constant.ProjectProcess.ON_GOING) {
                prjEntity.setProcessStatus(Constant.ProjectProcess.ON_GOING);
            }
            if (proccessStatus == Constant.ProjectProcess.FINISHED) {
                prjEntity.setProcessStatus(Constant.ProjectProcess.FINISHED);
            }
            projectRepository.save(prjEntity);

            if(requestStatus == Constant.RequestStatus.CLOSED){
                interactionRepository.deleteByProjectIdAndType(project.getId(),Constant.InteractionType.MATCH);
            }
            return "Successfully Update Project Status.";
        }
        return "Error Occured, Update has failed.";


    }

    @Override
    public Project getProjectById(int id) {
        return projectRepository.findById(id);
    }

    @Override
    public Project viewProjectDetails(int id){
        return projectRepository.findById(id);

    }

    @Override
    public boolean isProjectFull(int projectId){
        Project project = projectRepository.findById(projectId);
        if(project != null){
            int capacity = 0;
            List<ProjectRequirements> requirementList = (List)project.getProjectRequirementsById();
            for(ProjectRequirements requirement: requirementList){
                capacity += requirement.getQuantity();
            }
            if(capacity == 0){
                return  false;
            }
            int joinedCount = project.getJobsById().size();

            if(joinedCount == capacity){
                return true;
            }
        }

        return false;
    }

    @Transactional
    public String closeProject(int projectId){
        Project project = projectRepository.findById(projectId);
        if(project == null){
            return "Project not existed.";
        }
        //1. Chuyển status sang CLOSED.
        project.setRequestStatus(Constant.RequestStatus.CLOSED);
        projectRepository.save(project);
        //2. Xóa tất cả các Interaction với các resource khác
        interactionRepository.deleteByProjectId(projectId);
        return "Project is Closed.";
    }

}

