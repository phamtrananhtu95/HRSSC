package com.hrssc.service.impl;

import com.hrssc.domain.SearchModel;
import com.hrssc.entities.HumanResource;
import com.hrssc.entities.Project;
import com.hrssc.entities.User;
import com.hrssc.repository.HumanResourceRepository;
import com.hrssc.repository.ProjectRepository;
import com.hrssc.repository.UserRepository;
import com.hrssc.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("searchService")
public class SearchServiceImpl implements SearchService {

    @Autowired
    HumanResourceRepository humanResourceRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    UserRepository userRepository;
    @Override
    public List<HumanResource> searchResource(SearchModel searchData) {
        User user = userRepository.findById(searchData.getUserId());
        if(user == null ){
            return null;
        }

        String location = searchData.getLocation();
        String skill = searchData.getSkill();
        String company = searchData.getCompany();
        if(location.equals("All")){
            location = "";
        }
        if(skill.equals("All")){
            skill = "";
        }

        return humanResourceRepository.searchResource(user.getCompanyId(),skill,location,company);
    }

    @Override
    public List<Project> searchProject(SearchModel searchData) {
        User user = userRepository.findById(searchData.getUserId());
        if(user == null ){
            return null;
        }

        String location = searchData.getLocation();
        String skill = searchData.getSkill();
        String company = searchData.getCompany();
        if(location.equals("All")){
            location = "";
        }
        if(skill.equals("All")){
            skill = "";
        }

        return projectRepository.searchProject(user.getCompanyId(),skill,location,company);
    }
}
