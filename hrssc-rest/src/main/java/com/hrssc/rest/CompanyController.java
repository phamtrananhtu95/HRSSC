package com.hrssc.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrssc.domain.dto.CompanyDto;
import com.hrssc.domain.dto.RegisterDto;
import com.hrssc.entities.TemporaryInfo;
import com.hrssc.service.CompanyService;

@RestController
@RequestMapping("/company")
public class CompanyController {
	
	@Autowired
	private CompanyService companyService ;
	
//	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
//	public CompanyDto createCompany(@RequestBody CompanyDto company) {
//		return companyService.createCompany(company);
//	}
}
