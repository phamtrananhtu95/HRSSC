package com.hrssc.repository;

import com.hrssc.entities.Interaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InteractionRepository extends JpaRepository<Interaction, Integer> {

    List<Interaction> findByProjectId(int projectid);
    List<Interaction> findByHumanResourceId(int resourceId);
    List<Interaction> findByProjectIdAndType(int id, String type);
    List<Interaction> findByHumanResourceIdAndType(int id, String type);
    Interaction findByHumanResourceIdAndProjectId(int resourceId,int projectId);
    Interaction findByProjectIdAndHumanResourceId(int projectId, int resourceId);
    Interaction findById(int id);
    Interaction findByContractId(int contractId);


    void deleteByProjectIdAndType(int id, String type);
    void deleteByHumanResourceIdAndType(int id, String type);
    void deleteByHumanResourceIdAndTypeNot(int id, String type);
    void deleteByHumanResourceId(int id);
    void deleteByProjectId(int id);
}
