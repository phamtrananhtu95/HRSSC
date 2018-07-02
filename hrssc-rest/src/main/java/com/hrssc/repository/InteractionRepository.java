package com.hrssc.repository;

import com.hrssc.entities.Interaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InteractionRepository extends JpaRepository<Interaction, Integer> {

    List<Interaction> findByProjectIdAndType(int id, String type);
    List<Interaction> findByHumanResourceIdAndType(int id, String type);
    void deleteByProjectIdAndType(int id, String type);
    void deleteByHumanResourceIdAndType(int id, String type);
}
