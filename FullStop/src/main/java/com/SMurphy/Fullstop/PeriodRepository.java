package com.SMurphy.Fullstop;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface PeriodRepository extends JpaRepository<Period, Long> {
	@Query("SELECT u FROM Period u WHERE u.userName = ?1")
	Period findByUserName (String userName);

}
