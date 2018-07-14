package com.hrssc.rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.hrssc.domain.dto.ResponseStatus;
import com.hrssc.domain.jacksonview.ApplianceView;
import com.hrssc.domain.jacksonview.InvitationView;
import com.hrssc.entities.HumanResource;
import com.hrssc.entities.Interaction;
import com.hrssc.entities.Project;
import com.hrssc.repository.ProjectRepository;
import com.hrssc.service.InvitationService;
import com.hrssc.service.ProjectManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invitation")
public class InvitationController {

    @Autowired
    InvitationService invitationService;

    @Autowired
    ProjectManagementService projectManagementService;

    @Autowired
    ProjectRepository projectRepository;

    @PostMapping(value = "/invite")
    public ResponseStatus acceptResource(@RequestBody Interaction interaction){
       return new ResponseStatus(invitationService.inviteResource(interaction));

    }

    @JsonView(InvitationView.ListView.class)
    @GetMapping(value = "/get-all-invitation/{managerId}")
    public List<HumanResource> getAllInvitations(@PathVariable(value = "managerId") int managerId){
        return  invitationService.loadAllInvitationByManager(managerId);
    }

    @JsonView(ApplianceView.Listview.class)
    @GetMapping("/get-resource-offers/{managerId}")
    public List<Project> loadAllResourceAppliance(@PathVariable("managerId") int managerId){
        return  invitationService.loadAllOfferByManager(managerId);
    }


    @PostMapping(value = "/accept")
    public ResponseStatus acceptInvitation(@RequestBody Interaction invitation){

        if(projectManagementService.isProjectFull(invitation.getProjectId())){
            return new ResponseStatus("Project is already full, Accept Failed");
        }
        ResponseStatus response = new ResponseStatus(invitationService.acceptInvitation(invitation));

        if(projectManagementService.isProjectFull(invitation.getProjectId())){
           response.setMessage(response.getMessage()+", "+projectManagementService.closeProject(invitation.getProjectId()));
        }
        return  response;
    }

    @PostMapping(value = "/reject")
    public ResponseStatus rejectInvitation(@RequestBody Interaction invitation){
        ResponseStatus response = new ResponseStatus(invitationService.rejectInvitation(invitation));
        return response;
    }

    @GetMapping(value = "/is-invited/{resourceId}")
    public boolean checkInvited(@PathVariable(value = "resourceId")int resourceId){
        return invitationService.checkInvited(resourceId);
    }
}
