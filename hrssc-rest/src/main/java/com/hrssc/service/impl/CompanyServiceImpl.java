package com.hrssc.service.impl;

import com.hrssc.domain.Constant;
import com.hrssc.entities.Company;
import com.hrssc.entities.HumanResource;
import com.hrssc.entities.Project;
import com.hrssc.entities.User;
import com.hrssc.repository.CompanyRepository;
import com.hrssc.repository.HumanResourceRepository;
import com.hrssc.repository.ProjectRepository;
import com.hrssc.repository.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.hrssc.service.CompanyService;

import java.util.List;
import java.util.Optional;

@Service("companyService")
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    HumanResourceRepository humanResourceRepository;


    public Company viewCompanyDetails(int companyId) throws NotFoundException {
        Company detailsCompany = companyRepository.findById(companyId);
        if (detailsCompany == null || detailsCompany.getStatus() == Constant.CompanyStatus.DEACTIVATED){
            throw new NotFoundException("Khong tim thay company");
        }
        return  detailsCompany;
    }

    public List<Project> viewCompanyProject(int companyId){
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final Optional<User> user = userRepository.findByUsername(authentication.getName());
        User checkUser = user.get();
        List<Project> resultList;
        if(checkUser.getCompanyId() == companyId && checkUser.getRoleId() == Constant.UserRole.CHIEF){
            resultList = projectRepository.findByCompanyId(companyId);
        }else{
            resultList = projectRepository.findByCompanyIdAndRequestStatus(companyId, Constant.RequestStatus.OPENNING);
        }
        return resultList;
    }

    public List<HumanResource> viewCompanyResource(int companyId){
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final Optional<User> user = userRepository.findByUsername(authentication.getName());
        User checkUser = user.get();
        List<HumanResource> resultList;
        if(checkUser.getCompanyId() == companyId && checkUser.getRoleId() == Constant.UserRole.CHIEF){
            resultList = humanResourceRepository.findByCompanyId(companyId);
        }else {
            resultList = humanResourceRepository.findByCompanyIdAndStatus(companyId, Constant.ResourceStatus.AVAILABLE);
        }
        return resultList;
    }

}
