package com.hrssc.rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.hrssc.domain.jacksonview.HomeView;
import com.hrssc.entities.Company;
import com.hrssc.entities.HumanResource;
import com.hrssc.entities.Project;
import com.hrssc.service.CompanyService;
import com.hrssc.service.HumanResourceService;
import com.hrssc.service.ProjectManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    CompanyService companyService;

    @Autowired
    ProjectManagementService projectManagementService;

    @Autowired
    HumanResourceService humanResourceService;

    @JsonView(HomeView.Resource.class)
    @GetMapping("/resource-list/{userId}")
    public List<HumanResource> getResourceList(@PathVariable(value = "userId") int userId){
        return humanResourceService.getHomeResourceList(userId);
    }

    @JsonView(HomeView.Project.class)
    @GetMapping("/project-list/{userId}")
    public List<Project> getProjectList(@PathVariable(value = "userId") int userId){
        return projectManagementService.getHomeProjectList(userId);
    }

    @JsonView(HomeView.Company.class)
    @GetMapping("/company-list/{userId}")
    public List<Company> getCompanyList(@PathVariable(value = "userId") int userId){

        return companyService.getHomeCompanyList(userId);
    }
}
