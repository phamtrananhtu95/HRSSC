package com.hrssc.service;

import com.hrssc.entities.HumanResource;
import com.hrssc.entities.Interaction;
import com.hrssc.entities.Project;

import java.util.List;

public interface InvitationService {

    String inviteResource(Interaction interaction);
    List<HumanResource> loadAllInvitationByManager(int managerId);
    List<Project> loadAllOfferByManager(int managerId);
    String acceptInvitation(Interaction invitation);
    String rejectInvitation(Interaction invitation);
    boolean checkInvited(int resourceId);
}
