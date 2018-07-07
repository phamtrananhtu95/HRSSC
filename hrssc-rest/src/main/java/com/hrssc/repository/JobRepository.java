package com.hrssc.repository;

import com.hrssc.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Integer> {
    Job findByProjectIdAndHumanResourceId(int projectId,int humanResourceId);
    List<Job> findByProjectId(int projectId);
    List<Job> findByProjectIdAndStatus(int projectId, int status);
    List<Job> findByHumanResourceIdAndStatus(int resourceId, int status);
}
