package com.hrssc.rest;


import com.fasterxml.jackson.annotation.JsonView;
import com.hrssc.domain.dto.ResponseStatus;
import com.hrssc.domain.jacksonview.ApplianceView;
import com.hrssc.domain.jacksonview.InvitationView;
import com.hrssc.domain.jacksonview.ProjectView;
import com.hrssc.entities.Contract;
import com.hrssc.entities.HumanResource;
import com.hrssc.entities.Interaction;
import com.hrssc.entities.Project;
import com.hrssc.service.ApplianceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appliance")
public class ApplianceController {

    @Autowired
    private ApplianceService applianceService;

    @PostMapping("/apply")
    public ResponseStatus applyToProject(@RequestBody Interaction interaction){
        ResponseStatus reponse = new ResponseStatus(applianceService.applyToProject(interaction));
        return reponse;
    }

    @JsonView(ApplianceView.Listview.class)
    @GetMapping("/get-project-appliance/{managerId}")
    public List<Project> loadAllProjectAppliance(@PathVariable("managerId") int managerId){
        return  applianceService.loadAllProjectAppliance(managerId);
    }

    @JsonView(InvitationView.ListView.class)
    @GetMapping("/get-project-offers/{managerId}")
    public List<HumanResource> loadAllResourceAppliance(@PathVariable("managerId") int managerId){
        return  applianceService.loadAllResourceAppliance(managerId);
    }



    @PostMapping("/accept")
    public ResponseStatus acceptAppliance(@RequestBody Interaction interaction){
        ResponseStatus reponse = new ResponseStatus(applianceService.acceptAppliance(interaction));

        return reponse;
    }

    @PostMapping("/reject")
    public String rejectAppliance(@RequestBody Interaction interaction){
        return applianceService.rejectAppliance(interaction);
    }


}
