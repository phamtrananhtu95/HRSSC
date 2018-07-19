package com.hrssc.repository;

import com.hrssc.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company,Integer> {

    List<Company> findAll();
    Company findById(int id);
    Company findByEmail(String email);
    List<Company> findByIdNot(int id);
}