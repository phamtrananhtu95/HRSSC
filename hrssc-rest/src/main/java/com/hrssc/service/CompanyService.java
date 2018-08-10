package com.hrssc.service;

import com.hrssc.domain.dto.RegisterDto;
import com.hrssc.entities.Company;
import com.hrssc.entities.HumanResource;
import com.hrssc.entities.Project;
import javassist.NotFoundException;

import java.util.List;

public interface CompanyService {
    Company viewCompanyDetails(int companyId) throws NotFoundException;
    List<Project> viewCompanyProject(int companyId, int userId) throws NotFoundException;
    List<HumanResource> viewCompanyResource(int companyId, int userId)throws NotFoundException;
    String updateCompany(Company company);
    List<Company> getHomeCompanyList(int userId);
    String deactiveCompany(int companyId);


}
