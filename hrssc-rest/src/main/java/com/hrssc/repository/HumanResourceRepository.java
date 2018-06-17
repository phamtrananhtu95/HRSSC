package com.hrssc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrssc.entities.HumanResource;

@Repository
public interface HumanResourceRepository extends JpaRepository<HumanResource, Integer>{
	
	List<HumanResource> findAll();
}
