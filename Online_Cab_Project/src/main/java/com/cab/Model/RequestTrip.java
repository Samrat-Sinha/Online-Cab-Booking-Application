package com.cab.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestTrip {

	private Integer tripBookingId;
	private String pickupLocation;
	private String fromDateTime;
	private String dropLocation;
	private String toDateTime;
	private float distanceInKm;
	private String carType;
	private String CabStatus;
	private String BookingStatus;
	
	
	@JsonIgnore
	private Cab RequestCab;

	
}
