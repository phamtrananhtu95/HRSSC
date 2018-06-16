package com.hrssc.rest;

import com.hrssc.entities.TemporaryInfo;
import com.hrssc.service.ManageCompaniesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController("/manageCompanies")
public class CompaniesManagementController {

    @Autowired
    ManageCompaniesService manageCompaniesService;

    @GetMapping(value = "**/allRequests")
    private List<TemporaryInfo> loadAllRequest(){
        List<TemporaryInfo> requestList = manageCompaniesService.loadAllRequest();

        return requestList;

    }

}
