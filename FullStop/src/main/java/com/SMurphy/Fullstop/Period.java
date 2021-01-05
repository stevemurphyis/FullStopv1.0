package com.SMurphy.Fullstop;

import javax.persistence.*;

@Entity
@Table(name = "periods")
public class Period {
     
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 45)
    private String userName;   
    
    @Column(nullable = false, length = 45)
    private String startDay;
    
    @Column(nullable = false, length = 45)
    private String startMonth;
    
    @Column(nullable = false, length = 45)
    private String startYear;
    
    @Column(nullable = false, length =45)
    private String endDay;
    
    @Column(nullable = false, length = 45)
    private String endMonth;
    
    @Column(nullable = false, length = 45)
    private String endYear;
    
    @Column(nullable = false, length = 1)
    private String painLevel;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public String getStartDay() {
		return startDay;
	}

	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}

	public String getStartMonth() {
		return startMonth;
	}

	public void setStartMonth(String startMonth) {
		this.startMonth = startMonth;
	}

	public String getStartYear() {
		return startYear;
	}

	public void setStartYear(String startYear) {
		this.startYear = startYear;
	}

	public String getEndDay() {
		return endDay;
	}

	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}

	public String getEndMonth() {
		return endMonth;
	}

	public void setEndMonth(String endMonth) {
		this.endMonth = endMonth;
	}

	public String getEndYear() {
		return endYear;
	}

	public void setEndYear(String endYear) {
		this.endYear = endYear;
	}

	public String getPainLevel() {
		return painLevel;
	}

	public void setPainLevel(String painLevel) {
		this.painLevel = painLevel;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
     
}
