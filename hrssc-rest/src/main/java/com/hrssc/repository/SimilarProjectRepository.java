package com.hrssc.repository;

import com.hrssc.entities.SimilarProject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SimilarProjectRepository extends JpaRepository<SimilarProject,Integer> {
  void deleteByProjectId(int projectId);
  List<SimilarProject> findByProjectId(int projectId);
}
