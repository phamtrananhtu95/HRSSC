package com.hrssc.service.impl;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.hrssc.domain.Constant;
import com.hrssc.domain.dto.ChooseDomainDto;
import com.hrssc.entities.ChosenDomains;
import com.hrssc.entities.User;
import com.hrssc.repository.ChooseDomainRepository;
import com.hrssc.repository.UserRepository;
import com.hrssc.service.ChooseDomainService;

@Service
public class ChooseDomainServiceImpl implements ChooseDomainService {

	@Autowired
	private ChooseDomainRepository chooseDomainRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public ChooseDomainDto createChooseDomain(final ChooseDomainDto chooseDomain) {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		final Optional<User> user = userRepository.findByUsername(authentication.getName());

		if (!user.isPresent()) {
			return new ChooseDomainDto();
		}

		final ChosenDomains chosenDomain = chooseDomainRepository.save(ChosenDomains.builder()
				.positions(chooseDomain.getPositions().stream().collect(Collectors.joining(Constant.COMMA)))
				.locations(chooseDomain.getLocations().stream().collect(Collectors.joining(Constant.COMMA)))
				.userId(user.get().getId()).build());

		return new ChooseDomainDto(chosenDomain);
	}

}
