package com.hrssc.repository;

import com.hrssc.entities.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SkillRepository extends JpaRepository<Skill,Integer> {

    List<Skill> findByPositionId(int positionId);

    @Query(value="SELECT sk from skill sk GROUP BY sk.title")
    List<Skill> findDistinctSkill();
}
