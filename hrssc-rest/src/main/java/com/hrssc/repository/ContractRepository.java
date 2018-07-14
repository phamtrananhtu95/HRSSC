package com.hrssc.repository;

import com.hrssc.entities.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contract, Integer> {
    Contract findById(int contractId);
}
