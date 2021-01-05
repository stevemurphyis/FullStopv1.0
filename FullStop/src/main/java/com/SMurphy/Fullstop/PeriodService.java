package com.SMurphy.Fullstop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PeriodService {
	@Autowired
	private PeriodRepository repo;
	
	public List<Period> listAll() {		
		return repo.findAll();
	}
	
	public void save(Period period) {
		repo.save(period);
	}
	
	public Period get(Long id) {
		return repo.findById(id).get();
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}

}
