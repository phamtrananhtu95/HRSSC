package com.hrssc.service.impl;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrssc.domain.dto.CompanyDto;
import com.hrssc.entities.Company;
import com.hrssc.entities.TemporaryInfo;
import com.hrssc.repository.CompanyRepository;
import com.hrssc.repository.TemporaryInfoRepository;
import com.hrssc.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private TemporaryInfoRepository temporaryInfoRepository;

	@Override
	public CompanyDto createCompany(CompanyDto company) {

		TemporaryInfo temporaryInfo = TemporaryInfo.builder().companyName(company.getCompanyName())
				.companyAddress(company.getCompanyAddress()).companyCity(company.getCompanyCity())
				.companyCountry(company.getCompanyCountry()).companyTax(company.getCompanyTax())
				.companyEmail(company.getCompanyEmail()).companyTel(company.getCompanyTel())
				.representativeName(company.getRepresentativeName())
				.representtativeEmail(company.getRepresenttativeEmail())
				.representativeTel(company.getRepresentativeTel()).representativeTitle(company.getRepresentativeTitle())
				.build();

		temporaryInfoRepository.save(temporaryInfo);
		return company;
	}

}
