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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	UserRepository userRepository;

	@GetMapping()
	public UserDetails user(Principal principal) {

		Object tmp = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) tmp).getUsername();
		User user = userRepository.findByUsername(username)
				.orElseThrow(() ->
				new UsernameNotFoundException("User not found with username or email : " + username)
		);
		Collection<? extends GrantedAuthority> authorities =  ((UserDetails) tmp).getAuthorities();
		return MyPrincipal.create(user, authorities);
	}
}
