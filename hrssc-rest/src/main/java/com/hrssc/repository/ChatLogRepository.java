package com.hrssc.repository;

import com.hrssc.entities.ChatLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatLogRepository extends JpaRepository<ChatLog,Integer> {
    List<ChatLog> findByContractId(int contractId);
}
