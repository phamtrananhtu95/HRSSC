package com.hrssc.rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.hrssc.domain.dto.ResponseStatus;
import com.hrssc.domain.jacksonview.InvitationView;
import com.hrssc.entities.HumanResource;
import com.hrssc.entities.Interaction;
import com.hrssc.service.InvitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invitation")
public class InvitationController {

    @Autowired
    InvitationService invitationService;



    @PostMapping(value = "/invite")
    public void acceptResource(@RequestBody Interaction interaction){
        invitationService.inviteResource(interaction);

    }

    @JsonView(InvitationView.ListView.class)
    @GetMapping(value = "/get-all-invitation/{managerId}")
    public List<HumanResource> getAllInvitations(@PathVariable(value = "managerId") int managerId){
        return  invitationService.loadAllInvitationByManager(managerId);
    }
}
