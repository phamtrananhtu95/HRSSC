package com.hrssc.repository;

import com.hrssc.entities.PositionRequirements;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PositionRequirementsRepository extends JpaRepository<PositionRequirements, Integer> {
    List<PositionRequirements> findByProjectId(int id);
}
