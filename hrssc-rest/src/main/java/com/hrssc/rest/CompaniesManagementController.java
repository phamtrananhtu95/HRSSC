package com.hrssc.rest;

import com.hrssc.entities.TemporaryInfo;
import com.hrssc.service.CompaniesManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController("/manage-companies")
public class CompaniesManagementController {

    @Autowired
    CompaniesManagementService companiesManagementService;

    @GetMapping(value = "**/all-requests")
    private List<TemporaryInfo> loadAllRequest(){
        List<TemporaryInfo> requestList = companiesManagementService.loadAllRequest();

        return requestList;

    }

    @PostMapping(value = "**/accept-company")
    private boolean acceptCompany(@RequestParam("tempId") String tempInfoId){
        return companiesManagementService.acceptCompany(Integer.parseInt(tempInfoId));
    }

}
