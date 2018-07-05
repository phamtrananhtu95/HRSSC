package com.hrssc.repository;

import com.hrssc.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Integer> {
    Job findByProjectIdAndHumanResourceId(int projectId,int humanResourceId);
}
