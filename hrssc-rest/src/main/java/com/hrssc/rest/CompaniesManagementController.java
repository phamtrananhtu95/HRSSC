package com.hrssc.rest;

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
    private List<TemporaryInfo> loadAllRequest(){
        List<TemporaryInfo> requestList = companiesManagementService.loadAllRequest();

        return requestList;

    }


    @PostMapping(value = "/accept-company/{tempId}")
    private boolean acceptCompany(@PathVariable("tempId") int tempInfoId){
        return companiesManagementService.acceptCompany(tempInfoId);
    }

    @PostMapping(value = "/reject-company/{tempId}")
    private void rejectCompany(@PathVariable(value ="tempId") int tempId){
        companiesManagementService.removeTempInfo(tempId);
    }
}
