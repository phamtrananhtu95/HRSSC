package com.hrssc.repository;

import com.hrssc.entities.ResourceSkills;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Thien on 6/20/2018.
 */
public interface ResourceSkillRepository extends JpaRepository<ResourceSkills, Long> {

    List<ResourceSkills> getResourceSkillsByHumanResourceId(int id);
    void deleteByHumanResourceId(int id);

}
