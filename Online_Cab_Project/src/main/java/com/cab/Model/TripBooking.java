package com.cab.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TripBooking {

	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer tripBookingId;
	@NotNull
	private String pickupLocation;
	@NotNull
	private String fromDateTime;
	@NotNull
	private String dropLocation;
	@NotNull
	private String toDateTime;
	@NotNull
	private float distanceInKm;
	@JsonIgnore
	private String currStatus;
	
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JsonIgnore
	private Driver driver;
	
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JsonIgnore
	private Customer customer;
	
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JsonIgnore
	private Cab cab;

}
