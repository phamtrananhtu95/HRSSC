package com.hrssc.service.impl;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.hrssc.domain.dto.ChooseDomainDto;
import com.hrssc.entities.ChosenDomains;
import com.hrssc.entities.User;
import com.hrssc.repository.ChooseDomainRepository;
import com.hrssc.repository.UserRepository;
import com.hrssc.service.ChooseDomainService;

@Service
public class ChooseDomainServiceImpl implements ChooseDomainService{

	@Autowired
	private ChooseDomainRepository chooseDomainRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public ChooseDomainDto createChooseDomain(ChooseDomainDto chooseDomain) {
		User user;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		int userId = userRepository.findByUsername(currentPrincipalName).getId();
		ChosenDomains chosenDomain = ChosenDomains.builder()
				.positions(chooseDomain.getPositions())
				.locations(chooseDomain.getLocations())
				.userId(userId).build();
		chooseDomainRepository.save(chosenDomain);
		return chooseDomain;
	}
	
}
