package com.hrssc.repository;


import com.hrssc.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

    List<Project> getProjectByUserId(int userId);
    Project findByTitleAndUserId(String title, int userId);
    Project findById(int id);
    List<Project> findByRequestStatusAndCompanyIdNot(int status,int id);
    List<Project> findByProcessStatusNot(int status);
    List<Project> findByCompanyId(int companyid);
    List<Project> findByCompanyIdAndRequestStatus(int companyId, int requestStatus);
    List<Project> findByUserIdAndRequestStatus(int managerId, int status);
    List<Project> findByUserIdAndProcessStatus(int userId, int status);
    List<Project> findByUserId(int userId);
    List<Project> findByCompanyIdAndProcessStatusNot(int companyId, int processStatus);
    List<Project> findByUserIdAndRequestStatusNot(int userId, int requestStatus);

    @Query("SELECT prj FROM project prj " +
            "WHERE prj.companyId <>:companyId " +
            "  AND prj.requestStatus = '1'"+
            "  AND prj.processStatus <> '3'"+
            "  AND prj.companyByCompanyId.name LIKE CONCAT('%',:companyName,'%') " +
            "  AND prj.companyByCompanyId.city LIKE CONCAT('%',:location,'%') " +
            "  AND prj.id IN (SELECT prjr.projectId FROM project_requirements prjr " +
            "                      WHERE prjr.id IN (SELECT skr.projectRequirementsId FROM skill_requirements skr " +
            "                                                WHERE skr.skillBySkillId.title LIKE CONCAT('%',:skillName,'%')))")
    List<Project> searchProject(@Param(value = "companyId") int companyId,
                                      @Param(value = "skillName") String skillName,
                                      @Param(value = "location") String location,
                                      @Param(value = "companyName") String companyName);
    }
