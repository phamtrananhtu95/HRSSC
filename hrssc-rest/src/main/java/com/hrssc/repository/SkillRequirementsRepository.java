package com.hrssc.repository;

import com.hrssc.entities.SkillRequirements;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkillRequirementsRepository extends JpaRepository<SkillRequirements,Integer> {
        void deleteByProjectRequirementsId(int id);
        List<SkillRequirements> findByProjectRequirementsId(int id);
        SkillRequirements findById(int id);
}
