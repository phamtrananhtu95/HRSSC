package com.hrssc.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseStatus {
	private String code;
	private String message;
	
	public ResponseStatus(String message){
		this.message = message;
	}
}
