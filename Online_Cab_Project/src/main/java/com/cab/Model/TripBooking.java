package com.cab.Model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
	private String fromLocation;
	private String toLocation;
	private LocalDateTime fromDateTime;
	private LocalDateTime toDateTime;
	private boolean status;
	private float distanceInKm;
	private float bill;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private User user;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private Driver driver;
	
	
}
