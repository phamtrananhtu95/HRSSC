package com.hrssc.service.impl;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrssc.domain.dto.RegisterDto;
import com.hrssc.domain.dto.UserDto;
import com.hrssc.entities.TemporaryInfo;
import com.hrssc.repository.TemporaryInfoRepository;
import com.hrssc.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	private TemporaryInfoRepository temporaryInfoRepository;
	
	@Override
	public Boolean login(UserDto user) {
		
		UserDto userObj = UserDto.builder()
				.username(user.getUsername())
				.password("admin")
				.role("admin")
				.build();
		return user.getPassword().equals(userObj.getPassword());
	}

	@Override
	public RegisterDto register(RegisterDto register) {
		TemporaryInfo temporaryInfo = TemporaryInfo.builder().companyName(register.getCompanyName())
				.companyAddress(register.getCompanyAddress()).companyCity(register.getCompanyCity())
				.companyCountry(register.getCompanyCountry()).companyTax(register.getCompanyTax())
				.companyEmail(register.getCompanyEmail()).companyTel(register.getCompanyTel())
				.representativeName(register.getRepresentativeName())
				.representtativeEmail(register.getRepresenttativeEmail())
				.representativeTel(register.getRepresentativeTel()).representativeTitle(register.getRepresentativeTitle())
				.build();

		temporaryInfoRepository.save(temporaryInfo);
		return register;
	}
}
