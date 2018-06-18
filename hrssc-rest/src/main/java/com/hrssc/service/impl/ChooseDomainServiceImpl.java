package com.hrssc.service.impl;

import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
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

		final String positions = !chooseDomain.getPositions().isEmpty()
				? chooseDomain.getPositions().stream().collect(Collectors.joining(Constant.COMMA))
				: StringUtils.EMPTY;
		final String locations = !chooseDomain.getLocations().isEmpty()
				? chooseDomain.getLocations().stream().collect(Collectors.joining(Constant.COMMA))
				: StringUtils.EMPTY;

		final ChosenDomains chosenDomain = chooseDomainRepository.save(
				ChosenDomains.builder().positions(positions).locations(locations).userId(user.get().getId()).build());

		return new ChooseDomainDto(chosenDomain);
	}

}
