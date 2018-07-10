package com.hrssc.rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.hrssc.domain.jacksonview.CompanyView;
import com.hrssc.entities.Company;
import com.hrssc.entities.TemporaryInfo;
import com.hrssc.service.CompaniesManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/manage-companies")
@RestController()
public class CompaniesManagementController {

    @Autowired
    CompaniesManagementService companiesManagementService;

    @GetMapping(value = "/all-requests")
    public List<TemporaryInfo> loadAllRequest(){
        List<TemporaryInfo> requestList = companiesManagementService.loadAllRequest();

        return requestList;

    }


    @GetMapping(value = "/accept-company/{tempId}")
    public boolean acceptCompany(@PathVariable(value = "tempId") int tempInfoId){
        return companiesManagementService.acceptCompany(tempInfoId);
    }

    @PostMapping(value = "/reject-company/{tempId}")
    public void rejectCompany(@PathVariable(value ="tempId") int tempId){
        companiesManagementService.removeTempInfo(tempId);
    }

    @JsonView(CompanyView.info.class)
    @GetMapping(value = "/get-company-list")
    public List<Company> getCompanyList(){
        return companiesManagementService.getCompanyList();
    }
}
