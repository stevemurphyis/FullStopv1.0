package com.SMurphy.Fullstop;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.annotation.Rollback;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class PeriodRepositoryTests {
	
	@Autowired
	private PeriodRepository repo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreatePeriod() {
	    Period period = new Period();
	    period.setUserName("bowie");
	    period.setStartDay("3");
	    period.setStartMonth("1");
	    period.setStartYear("2021");
	    period.setEndDay("8");
	    period.setEndMonth("1");
	    period.setEndYear("2021");
	    period.setPainLevel("4");
	     
	    Period savedPeriod = repo.save(period);
	     
	     
	}
	@Test
	public void testFindUserByUserName() {
		String userName = "bowie";
		
		Period period = repo.findByUserName(userName);
		
		assertThat(period).isNotNull();
	}
}
