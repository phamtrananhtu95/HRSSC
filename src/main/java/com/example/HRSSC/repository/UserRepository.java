package com.example.HRSSC.repository;

import com.example.HRSSC.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Thien on 6/12/2018.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

    User findByUsername(String username);
}
