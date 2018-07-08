package com.hrssc.rest;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import com.hrssc.domain.jacksonview.CompanyView;
import com.hrssc.domain.jacksonview.HumanResourceView;
import com.hrssc.domain.jacksonview.ProjectView;
import com.hrssc.entities.Company;
import com.hrssc.entities.HumanResource;
import com.hrssc.entities.Project;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.hrssc.domain.dto.CompanyDto;
import com.hrssc.domain.dto.RegisterDto;
import com.hrssc.entities.TemporaryInfo;
import com.hrssc.service.CompanyService;

@RestController
@RequestMapping("/company")
public class CompanyController {
	
	@Autowired
	private CompanyService companyService ;

	@JsonView(CompanyView.info.class)
	@GetMapping(value = "/details/{companyId}")
	public Company viewCompanyDetails(@PathVariable("companyId") int companyId) throws NotFoundException {
		return companyService.viewCompanyDetails(companyId);
	}

	@JsonView(ProjectView.ListView.class)
	@GetMapping(value = "/project-list/{userId}/{companyId}")
	public List<Project> viewCompanyProject(@PathVariable("companyId") int companyId,
											@PathVariable("userId") int userId) throws NotFoundException{
		return companyService.viewCompanyProject(companyId, userId);
	}

	@JsonView(HumanResourceView.overview.class)
	@GetMapping(value = "/resource-list/{userId}/{companyId}")
	public List<HumanResource> viewCompanyResource(@PathVariable("companyId") int companyId,
												   @PathVariable("userId") int userId) throws NotFoundException{
		return companyService.viewCompanyResource(companyId, userId);
	}
//	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
//	public CompanyDto createCompany(@RequestBody CompanyDto company) {
//		return companyService.createCompany(company);
//	}
}
