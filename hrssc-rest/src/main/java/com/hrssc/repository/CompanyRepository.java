package com.hrssc.repository;

import com.hrssc.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Integer> {

    Company findById(int id);
    Company findByEmail(String email);
}