package com.hrssc.repository;

import com.hrssc.entities.ContractVersion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContractVersionRepository extends JpaRepository<ContractVersion, Integer> {
    List<ContractVersion> findByContractId(int contractId);
}
