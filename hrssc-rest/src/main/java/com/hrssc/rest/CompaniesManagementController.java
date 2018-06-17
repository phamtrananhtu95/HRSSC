package com.hrssc.rest;

import com.hrssc.entities.TemporaryInfo;
import com.hrssc.service.ManageCompaniesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController("/manage-companies")
public class CompaniesManagementController {

    @Autowired
    ManageCompaniesService manageCompaniesService;

    @GetMapping(value = "**/all-requests")
    private List<TemporaryInfo> loadAllRequest(){
        List<TemporaryInfo> requestList = manageCompaniesService.loadAllRequest();

        return requestList;

    }

    @PostMapping(value = "**/accept-company")
    private boolean acceptCompany(@RequestParam("tempId") String tempInfoId){
        return manageCompaniesService.acceptCompany(Integer.parseInt(tempInfoId));
    }

}
