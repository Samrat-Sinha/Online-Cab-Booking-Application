package com.cab.Model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Driver {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer driverId;
	private String driverFirstName;
	private String driverLastName;
	private String licenseNo;
	private float rating;
	private String currentLocation;
	
	
	private boolean onAnotherRide; 
	
	@OneToOne(cascade = CascadeType.ALL)
	private Cab cab;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "driver")
	@JsonIgnore
	List<TripBooking> tripBooking = new ArrayList<>(); 
	
}