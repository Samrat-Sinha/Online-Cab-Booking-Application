package com.cab.Model;


import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TripBookingDTO {


	private String firstName;
	private String lastName;
	
	private Integer tripBookingId;
	
	private String fromLocation;
	private String toLocation;
	
	private String fromDate;
	private String toDate;
	
	private String driverFirstName;
	private String driverLastName;
	private String licenseNo;
	
	
	@Enumerated(EnumType.STRING)
	private CarType carType;
	private String carName;
	private String carNumber;
	private float rating;
	
	private float bill;
	
	
	

	
}
