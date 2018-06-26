package com.hrssc.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyDto {
	private String companyName;
	private String companyAddress;
	private String companyCity;
	private String companyCountry;
	private String companyTax;
	private String companyEmail;
	private String companyTel;
	private String representativeName;
	private String representtativeEmail;
	private String representativeTel;
	private String representativeTitle;
}
