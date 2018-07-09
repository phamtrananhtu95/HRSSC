package com.hrssc.repository;

import com.hrssc.entities.SimilarResource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SimilarResourceRepository extends JpaRepository<SimilarResource,Integer> {
    void deleteByHumanResourceId(int id);
    List<SimilarResource> findByHumanResourceId(int resourceId);
}
