package com.hrssc.repository;

import com.hrssc.entities.ProjectRequirements;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRequirementRepository extends JpaRepository<ProjectRequirements,Integer> {
    ProjectRequirements  findByProjectIdAndPositionIdAndPayment(int prjId, int positionId, int payment);
    List<ProjectRequirements> findByProjectId(int id);
    ProjectRequirements findById(int id);
}
