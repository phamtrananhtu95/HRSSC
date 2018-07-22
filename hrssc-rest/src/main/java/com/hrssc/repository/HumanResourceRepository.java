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


    List<HumanResource> getHumanResourcesByUserId(int userId);


    HumanResource findByEmail(String email);

    List<HumanResource> findByStatusAndCompanyIdNot(int status,int companyId);

    HumanResource getById(int id);

    List<HumanResource> findByCompanyId(int companyId);
    List<HumanResource> findByCompanyIdAndStatus(int companyId, int status);
    List<HumanResource> findByUserIdAndStatus(int userId, int status);


    @Query("SELECT resource FROM human_resource resource " +
            "WHERE resource.companyId <>:companyId " +
            "  AND resource.companyByCompanyId.name LIKE CONCAT('%',:companyName,'%') " +
            "  AND resource.companyByCompanyId.city LIKE CONCAT('%',:location,'%') " +
            "  AND resource.id IN (SELECT skr.humanResourceId from resource_skills skr " +
            "                      WHERE skr.skillBySkillId.title LIKE CONCAT('%',:skillName,'%'))")
    List<HumanResource> searchResource(@Param(value = "companyId") int companyId,
                                       @Param(value = "skillName") String skillName,
                                       @Param(value = "location") String location,
                                       @Param(value = "companyName") String companyName);




}
