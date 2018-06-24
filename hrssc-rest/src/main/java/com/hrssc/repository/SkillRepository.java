package com.hrssc.repository;

import com.hrssc.entities.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkillRepository extends JpaRepository<Skill,Integer> {

    List<Skill> findByPositionId(int positionId);
}
