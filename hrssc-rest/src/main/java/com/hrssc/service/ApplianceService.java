package com.hrssc.service;

import com.hrssc.entities.Interaction;
import com.hrssc.entities.Project;

import java.util.List;

public interface ApplianceService {
    String applyToProject(Interaction interaction);
    List<Project> loadAllInteraction(int managerID);
    String acceptAppliance(Interaction interaction);
    String rejectAppliance(Interaction interaction);
}
