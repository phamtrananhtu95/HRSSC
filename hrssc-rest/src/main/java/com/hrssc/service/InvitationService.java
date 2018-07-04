package com.hrssc.service;

import com.hrssc.entities.HumanResource;
import com.hrssc.entities.Interaction;

import java.util.List;

public interface InvitationService {

    void inviteResource(Interaction interaction);
    List<HumanResource> loadAllInvitationByManager(int managerId);
}
