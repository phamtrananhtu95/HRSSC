package com.hrssc.repository;

import com.hrssc.entities.SkillRequirements;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkillRequirementsRepository extends JpaRepository<SkillRequirements,Integer> {
    List<SkillRequirements> findByProjectId(int id);
}
