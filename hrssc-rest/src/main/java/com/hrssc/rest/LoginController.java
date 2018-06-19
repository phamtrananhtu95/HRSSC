package com.hrssc.rest;

import java.security.Principal;
import java.util.Collection;

import com.hrssc.domain.dto.MyPrincipal;
import com.hrssc.entities.User;
import com.hrssc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.hrssc.domain.dto.RegisterDto;
import com.hrssc.service.LoginService;

@RestController
@RequestMapping("")
public class LoginController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	LoginService loginService;

	@GetMapping(value = "/login")
	public UserDetails user(Principal principal) {

		Object tmp = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) tmp).getUsername();
		User user = userRepository.findByUsername(username).orElseThrow(
				() -> new UsernameNotFoundException("User not found with username or email : " + username));
		Collection<? extends GrantedAuthority> authorities = ((UserDetails) tmp).getAuthorities();
		return MyPrincipal.create(user, authorities);
	}

	@PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
	public RegisterDto register(@RequestBody RegisterDto company) {
		return loginService.register(company);
	}
}
