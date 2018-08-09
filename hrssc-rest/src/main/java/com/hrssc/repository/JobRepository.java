package com.hrssc.repository;

import com.hrssc.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Integer> {
    Job findById(int jobId);
    Job findByContractId(int contractId);
    Job findByProjectIdAndHumanResourceId(int projectId,int humanResourceId);
    List<Job> findByProjectId(int projectId);
    @Query(value = "SELECT j FROM job j WHERE j.projectId =:projectId AND (j.status = '1' OR j.status = '2')")
    List<Job> getJoinedResource(@Param("projectId") int projectId);
    List<Job> findByProjectIdAndStatus(int projectId, int status);
    List<Job> findByHumanResourceIdAndStatus(int resourceId, int status);
    List<Job> findByHumanResourceId(int resourceId);
    List<Job> findByStatus(int status);
}
