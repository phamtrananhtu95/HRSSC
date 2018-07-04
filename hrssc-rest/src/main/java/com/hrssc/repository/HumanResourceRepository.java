package com.hrssc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hrssc.entities.HumanResource;

@Repository
public interface HumanResourceRepository extends JpaRepository<HumanResource, Integer>{
	
	List<HumanResource> findAll();


    List<HumanResource> getHumanResourcesByUserId(int user_id);


    HumanResource findByEmail(String email);

    List<HumanResource> findByStatusAndCompanyIdNot(int status,int companyId);

    HumanResource getById(int id);

}
