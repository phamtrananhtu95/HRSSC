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
	 private int id;
	    private String name;
	    private String address;
	    private String city;
	    private String tel;
	    private String email;
	    private int status;
	    private String logo;
}
