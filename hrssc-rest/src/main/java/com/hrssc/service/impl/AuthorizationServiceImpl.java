package com.hrssc.service.impl;

import com.hrssc.domain.Constant;
import com.hrssc.entities.HumanResource;
import com.hrssc.entities.Job;
import com.hrssc.entities.Project;
import com.hrssc.entities.User;
import com.hrssc.repository.HumanResourceRepository;
import com.hrssc.repository.JobRepository;
import com.hrssc.repository.ProjectRepository;
import com.hrssc.repository.UserRepository;
import com.hrssc.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;

import java.util.Optional;

@Service("authorizationService")
public class AuthorizationServiceImpl implements AuthorizationService {

    @Autowired
    ProjectRepository projectRepo;

    @Autowired
    UserRepository userRepo;

    @Autowired
    HumanResourceRepository resourceRepo;

    @Autowired
    JobRepository jobRepository;

    @Override
    public boolean checkProject(int projectId, int userId){
        User user = userRepo.findById(userId);
        if(user == null){
            return false;
        }
        int roleId = user.getRoleId();

        Project project = projectRepo.findById(projectId);
        if(project == null){
            return false;
        }

        //Only check on closed projects
        if(project.getRequestStatus() == Constant.RequestStatus.CLOSED) {
            //Update later: Stranger Manager can still see this project, as long as they have the connection.

            if (roleId == Constant.UserRole.CHIEF) {
                return project.getCompanyId() == user.getCompanyId();
            } else if (roleId == Constant.UserRole.MANAGER) {
                return project.getUserId() == userId;

            }
        }else if(project.getRequestStatus() == Constant.RequestStatus.OPENNING){
            //Nếu là project cùng công ty thì chỉ có Chief hoặc Manager của project đó mới có quyền xem thông tin
            //Manager của công ty B được phép thấy bất kì OPENNING project nào của cty A.
            if(project.getCompanyId() == user.getCompanyId()){
                if(roleId == Constant.UserRole.MANAGER){
                    return project.getUserId() == userId;
                }
            }
        }
        return true;
    }

    @Override
    public boolean checkResource(int resourceId, int userId){
        User user = userRepo.findById(userId);
        if(user == null){
            return false;
        }
        int roleId = user.getRoleId();

        Optional<HumanResource> resourceOptional = resourceRepo.findById(resourceId);
        if(!resourceOptional.isPresent()){
            return false;
        }
        HumanResource resource  = resourceOptional.get();

        if(resource.getStatus() == Constant.ResourceStatus.INACTIVE) {
            //Trường hợp Resource trong trạng thái inactive thì chỉ có Chief hoặc Manager của resource đó mới có quyền xem thông tin.
            if (roleId == Constant.UserRole.CHIEF) {
                return resource.getCompanyId() == user.getCompanyId();

            } else if (roleId == Constant.UserRole.MANAGER) {
                return resource.getUserId() == userId;
            }
        }else if(resource.getStatus() == Constant.ResourceStatus.AVAILABLE){
            //Nếu là resource cùng công ty thì chỉ có Chief hoặc Manager của resource đó mới có quyền xem thông tin
            //Manager của công ty B được phép thấy bất kì Available resource nào của cty A.
            if(resource.getCompanyId() == user.getCompanyId()){
                if(roleId == Constant.UserRole.MANAGER){
                    return resource.getUserId() == userId;
                }
            }
        }
        return true;
    }

    @Override
    public boolean checkResource(int resourceId){
        Authentication authenticatedUser = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> userOptional = userRepo.findByUsername(authenticatedUser.getName());
        if(!userOptional.isPresent()){
            return false;
        }
        User user = userOptional.get();

        int roleId = user.getRoleId();

        Optional<HumanResource> resourceOptional = resourceRepo.findById(resourceId);
        if(!resourceOptional.isPresent()){
            return false;
        }
        HumanResource resource  = resourceOptional.get();

        if(resource.getStatus() == Constant.ResourceStatus.INACTIVE) {
            //Trường hợp Resource trong trạng thái inactive thì chỉ có Chief hoặc Manager của resource đó mới có quyền xem thông tin.
            if (roleId == Constant.UserRole.CHIEF) {
                return resource.getCompanyId() == user.getCompanyId();

            } else if (roleId == Constant.UserRole.MANAGER) {
                return resource.getUserId() == user.getId();
            }
        }else if(resource.getStatus() == Constant.ResourceStatus.AVAILABLE){
            //Nếu là resource cùng công ty thì chỉ có Chief hoặc Manager của resource đó mới có quyền xem thông tin
            //Manager của công ty B được phép thấy bất kì Available resource nào của cty A.
            if(resource.getCompanyId() == user.getCompanyId()){
                if(roleId == Constant.UserRole.MANAGER){
                    return resource.getUserId() == user.getId();
                }
            }
        }
        return true;
    }

    public boolean checkRoleRelease(int jobId, int userId){
        User user = userRepo.findById(userId);
        if (user == null) {
            return false;
        }
        if (user.getRoleId() == Constant.UserRole.ADMIN){
            return true;
        }
        Job job = jobRepository.findById(jobId);
        if(job == null){
            return false;
        }
        Project project = projectRepo.findById(job.getProjectId());
        if (project == null){
            return false;
        }
        if (user.getRoleId() == Constant.UserRole.CHIEF && user.getCompanyId() == project.getCompanyId()){
            return true;
        }
        if (project.getUserId() == userId){
            return true;
        }
        return false;
    }

    public boolean checkRoleReject(int jobId, int userId){
        User user = userRepo.findById(userId);
        if (user == null) {
            return false;
        }
        Job job = jobRepository.findById(jobId);
        if(job == null){
            return false;
        }
        Project project = projectRepo.findById(job.getProjectId());
        if (project == null){
            return false;
        }
        if (user.getRoleId() == Constant.UserRole.CHIEF && user.getCompanyId() == project.getCompanyId()){
            return true;
        }
        if (project.getUserId() == userId){
            return true;
        }
        return false;
    }
}
