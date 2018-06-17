package com.hrssc.service.impl;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrssc.domain.dto.CompanyDto;
import com.hrssc.entities.Company;
import com.hrssc.repository.CompanyRepository;
import com.hrssc.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService{

	@Autowired
	private CompanyRepository companyRepo;
	
	@Override
	public CompanyDto createCompany(CompanyDto company) {
		
		Company companyEntity = Company.builder().address(company.getAddress()).city(company.getCity())
				.email(company.getEmail()).name(company.getName()).
				tel(company.getTel()).status(company.getStatus()).logo(company.getLogo()).build();
		companyRepo.save(companyEntity);
		return company;
	}
	
}
