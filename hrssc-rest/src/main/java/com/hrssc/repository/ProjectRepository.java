package com.hrssc.repository;

import com.hrssc.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

    List<Project> getProjectByUserId(int userId);
    Project findByTitleAndUserId(String title, int userId);
    Project findById(int id);
}
