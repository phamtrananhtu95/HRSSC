package com.hrssc.repository;

import com.hrssc.entities.ChosenDomains;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChooseDomainRepository extends JpaRepository<ChosenDomains,Integer>{
	ChosenDomains findById(int id);
}
