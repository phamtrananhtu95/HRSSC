package com.hrssc.domain.dto;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.hrssc.domain.Constant;
import com.hrssc.entities.ChosenDomains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChooseDomainDto {
	private List<String> positions;
	private List<String> locations;

	public ChooseDomainDto(final ChosenDomains chosenDomains) {
		this.positions = StringUtils.isBlank(chosenDomains.getPositions()) ? Arrays
				.asList(chosenDomains.getPositions().split(Constant.COMMA)).stream().collect(Collectors.toList())
				: Collections.emptyList();
		this.locations = StringUtils.isBlank(chosenDomains.getLocations()) ? Arrays
				.asList(chosenDomains.getLocations().split(Constant.COMMA)).stream().collect(Collectors.toList())
				: Collections.emptyList();
	}
}
