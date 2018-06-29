package com.hrssc.repository;

import com.hrssc.entities.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Thien on 6/16/2018.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByUsername(final String username);

	User findByEmail(String email);

	List<User> findByFullname(String fullname);

	User findById(int id);


	User findByIdAndRoleId(int id, int roleId);

	@Query(value = "SELECT u FROM user u WHERE u.companyId=:companyId AND u.roleId=:managerRoleId")
	List<User> getByCompanyIdAndRoleId(@Param(value = "companyId") int companyId,
			@Param(value = "managerRoleId") int managerRoleId);

}
