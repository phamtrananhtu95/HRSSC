package com.hrssc.service;

import com.hrssc.entities.HumanResource;
import com.hrssc.entities.Interaction;
import com.hrssc.entities.Project;

import java.util.List;

public interface ApplianceService {
    String applyToProject(Interaction interaction);
    List<Project> loadAllProjectAppliance(int managerId);
    List<HumanResource> loadAllResourceAppliance(int managerId);

    String acceptAppliance(Interaction interaction);
    String rejectAppliance(Interaction interaction);
}
