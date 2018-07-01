package com.hrssc.service.impl;

import com.hrssc.domain.Constant;
import com.hrssc.entities.HumanResource;
import com.hrssc.entities.Project;
import com.hrssc.entities.User;
import com.hrssc.repository.HumanResourceRepository;
import com.hrssc.repository.ProjectRepository;
import com.hrssc.repository.UserRepository;
import com.hrssc.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
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

        if(roleId == Constant.UserRole.CHIEF){
            return project.getCompanyId() == user.getCompanyId();
        }else if(roleId == Constant.UserRole.MANAGER){
            return project.getUserId() == userId;

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
        if(roleId == Constant.UserRole.CHIEF){
           return resource.getCompanyId() == user.getCompanyId();

        }else if(roleId == Constant.UserRole.MANAGER){
            return resource.getUserId() == userId;
        }
        return true;
    }
}
