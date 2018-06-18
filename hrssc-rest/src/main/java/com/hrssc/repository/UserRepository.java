package com.hrssc.repository;

import com.hrssc.entities.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Thien on 6/16/2018.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    
	Optional<User> findByUsername(final String username);
    
	User findByEmail(String email);

}
