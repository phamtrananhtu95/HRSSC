package com.hrssc.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrssc.domain.dto.ChooseDomainDto;
import com.hrssc.service.ChooseDomainService;

@RestController
@RequestMapping("/chooseDomain")
public class ChooseDomainController {

	@Autowired
	private ChooseDomainService chooseDomainService;

	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ChooseDomainDto createChooseDomain(@RequestBody ChooseDomainDto chooseDomain) {
		return chooseDomainService.createChooseDomain(chooseDomain);
	}
}
