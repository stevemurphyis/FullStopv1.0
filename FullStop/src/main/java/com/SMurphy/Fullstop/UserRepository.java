package com.SMurphy.Fullstop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("SELECT u FROM User u WHERE u.userName = :userName")
	public User getUserByUserName(@Param("userName") String userName);
	
	@Query("SELECT u FROM User u WHERE u.userName = ?1")
	User findByUserName (String userName);
}
