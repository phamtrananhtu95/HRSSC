package com.hrssc.rest;

import java.security.Principal;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

	@GetMapping()
	public Principal user(Principal principal) {
		return principal;
	}
}
