package com.hrssc.rest;


import com.fasterxml.jackson.annotation.JsonView;
import com.hrssc.domain.jacksonview.ApplianceView;
import com.hrssc.domain.jacksonview.ProjectView;
import com.hrssc.entities.Interaction;
import com.hrssc.entities.Project;
import com.hrssc.service.ApplianceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appliance")
public class ApplianceController {

    @Autowired
    private ApplianceService applianceService;

    @PostMapping("/apply")
    public String applyToProject(@RequestBody Interaction interaction){
        return applianceService.applyToProject(interaction);
    }

    @JsonView(ApplianceView.Listview.class)
    @GetMapping("/get-all-appliance/{managerID}")
    public List<Project> loadAllAppliance(@PathVariable("managerID") int managerID){
        return  applianceService.loadAllInteraction(managerID);
    }

    @PostMapping("/accept")
    public String acceptAppliance(@RequestBody Interaction interaction){

        return applianceService.acceptAppliance(interaction);
    }

    @PostMapping("/reject")
    public String rejectAppliance(@RequestBody Interaction interaction){
        return applianceService.rejectAppliance(interaction);
    }
}
