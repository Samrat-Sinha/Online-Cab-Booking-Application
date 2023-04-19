package com.cab.Model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Driver extends User{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer driverId;
	private String licenceNo;
	private float rating;
	private String currLocation;
	private String currDriverStatus;
	
	@OneToMany(cascade = CascadeType.PERSIST,mappedBy = "driver")
	@JsonIgnore
	List<TripBooking> trips = new ArrayList<>();
	
	@OneToOne
	@JsonIgnore
	private Cab cab;
	
}
