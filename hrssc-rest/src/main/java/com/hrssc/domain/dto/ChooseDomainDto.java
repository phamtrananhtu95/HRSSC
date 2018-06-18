package com.hrssc.domain.dto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
		this.positions = Arrays.asList(chosenDomains.getPositions().split(Constant.COMMA)).stream()
				.collect(Collectors.toList());
		this.locations = Arrays.asList(chosenDomains.getLocations().split(Constant.COMMA)).stream()
				.collect(Collectors.toList());
	}
}
