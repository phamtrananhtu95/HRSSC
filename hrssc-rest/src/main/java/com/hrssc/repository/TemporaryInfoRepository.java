package com.hrssc.repository;

import com.hrssc.entities.TemporaryInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface TemporaryInfoRepository extends JpaRepository<TemporaryInfo, Integer> {

   TemporaryInfo findById(int id);
   void deleteById(int id);

}
