package com.hrssc.service.impl;

import com.hrssc.domain.Constant;
import com.hrssc.entities.*;
import com.hrssc.repository.*;
import com.hrssc.service.ProjectManagementService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.hrssc.service.CompanyService;

import javax.transaction.Transactional;
import java.util.ArrayList;
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

    @Autowired
    ProjectManagementService projectManagementService;

    @Autowired
    JobRepository jobRepository;

    public List<Company> getHomeCompanyList(int userId){
        User user = userRepository.findById(userId);
        if(user == null){
            return null;
        }
        List<Company> companyList = companyRepository.findByIdNot(user.getCompanyId());
        if(companyList == null){
            return null;
        }
        List<Company> resultList = new ArrayList<>();
        for(int i = companyList.size() -1; i > companyList.size() - 7; i--){
            if(i == -1){
                break;
            }
            resultList.add(companyList.get(i));
        }
        return resultList;
    }

    public Company viewCompanyDetails(int companyId) throws NotFoundException {
        Company detailsCompany = companyRepository.findById(companyId);
        if (detailsCompany == null || detailsCompany.getStatus() == Constant.CompanyStatus.DEACTIVATED){
            throw new NotFoundException("Khong tim thay company");
        }
        return  detailsCompany;
    }

    public List<Project> viewCompanyProject(int companyId, int userId) throws NotFoundException{
        User checkUser = userRepository.findById(userId);
        if(checkUser == null){
            throw new NotFoundException("User not found");
        }
        List<Project> resultList;
        if(checkUser.getCompanyId() == companyId && checkUser.getRoleId() == Constant.UserRole.CHIEF){
            resultList = projectRepository.findByCompanyId(companyId);
        }else{
            resultList = projectRepository.findByCompanyIdAndRequestStatus(companyId, Constant.RequestStatus.OPENNING);
        }
        return resultList;
    }

    public List<HumanResource> viewCompanyResource(int companyId, int userId)throws NotFoundException{
        User checkUser = userRepository.findById(userId);
        if(checkUser == null){
            throw new NotFoundException("User not found");
        }
        List<HumanResource> resultList;
        if(checkUser.getCompanyId() == companyId && checkUser.getRoleId() == Constant.UserRole.CHIEF){
            resultList = humanResourceRepository.findByCompanyId(companyId);
        }else {
            resultList = humanResourceRepository.findByCompanyIdAndStatus(companyId, Constant.ResourceStatus.AVAILABLE);
        }
        return resultList;
    }

    public  String updateCompany(Company company){
        Company companyEntity = companyRepository.findById(company.getId());
        if(companyEntity == null){
            return "Company does not existed.";
        }
        //companyEntity.setCity(company.getCity());
        //companyEntity.setAddress(company.getAddress());
        //companyEntity.setName(company.getName());
        companyEntity.setEmail(company.getEmail());
        companyEntity.setDescription(company.getDescription());
        companyEntity.setTel(company.getTel());
        companyRepository.save(companyEntity);
        return "Successfully Update Company";

    }

    @Transactional
    public String deactiveCompany(int companyId){
        Company company = companyRepository.findById(companyId);
        if(company == null){
            return "Company not Found!";
        }
        List<User> userList = userRepository.findByCompanyId(companyId);
        if(userList.size() != 0){
            for (User u: userList) {
                u.setStatus(Constant.ManagerStatus.DEACTIVATED);
                userRepository.save(u);
            }
        }
        List<Project> projectList = projectRepository.findByCompanyIdAndProcessStatusNot(companyId, Constant.ProjectProcess.FINISHED);
        if (projectList.size() != 0){
            for (Project p: projectList) {
                try {
                    projectManagementService.closeFinishedProject(p.getId());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        List<HumanResource> humanResourceList = humanResourceRepository.findByCompanyIdAndStatusNot(companyId, Constant.ResourceStatus.REMOVED);
        if (humanResourceList.size() != 0){
            for (HumanResource h: humanResourceList) {
                if(h.getStatus() == Constant.ResourceStatus.BUSY){
                    List<Job> jobList1 = jobRepository.findByHumanResourceIdAndStatus(h.getId(), Constant.JobStatus.PENDING);
                    List<Job> jobList2 = jobRepository.findByHumanResourceIdAndStatus(h.getId(), Constant.JobStatus.ON_GOING);
                    if(jobList1.size() != 0){
                        for (Job j: jobList1) {
                            j.setStatus(Constant.JobStatus.CANCEL);
                            jobRepository.save(j);
                        }
                    }
                    if (jobList2.size() != 0){
                        for (Job j: jobList2) {
                            j.setStatus(Constant.JobStatus.CANCEL);
                            jobRepository.save(j);
                        }
                    }
                }
                h.setStatus(Constant.ResourceStatus.INACTIVE);
                humanResourceRepository.save(h);
            }
        }

        return "Success!";
    }

}
