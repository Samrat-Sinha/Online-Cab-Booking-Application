package com.cab.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cab.Exception.CurrentUserSessionException;
import com.cab.Exception.TripBookingException;
import com.cab.Exception.UserException;
import com.cab.Model.TripBooking;
import com.cab.Service.TripBookingService;

@RestController
@RequestMapping("/OnlineCabBookingApplication/tripBooking")
public class TripBookingController {
	
	@Autowired
	private TripBookingService tbService;
	
	@PostMapping("/addnewTrip")
	public ResponseEntity<TripBooking> insertTripBookingHandler(@RequestBody TripBooking tripBooking, @RequestParam String uuid) throws TripBookingException, CurrentUserSessionException, UserException{
		return new ResponseEntity<TripBooking>(tbService.insertTripBooking(tripBooking, uuid),HttpStatus.CREATED);
	}
	

}