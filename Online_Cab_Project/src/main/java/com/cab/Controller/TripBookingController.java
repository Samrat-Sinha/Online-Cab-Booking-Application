package com.cab.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cab.Exception.CurrentUserSessionException;
import com.cab.Exception.TripBookingException;
import com.cab.Exception.UserException;
import com.cab.Model.TripBooking;
import com.cab.Model.TripBookingDTO;
import com.cab.Service.TripBookingService;

@RestController
@RequestMapping("/OnlineCabBookingApplication/tripBooking")
public class TripBookingController {
	
	@Autowired
	private TripBookingService tbService;
	
	@PostMapping("/addnewTrip")
	public ResponseEntity<TripBookingDTO> insertTripBookingHandler(@RequestBody TripBooking tripBooking, @RequestParam String uuid) throws TripBookingException, CurrentUserSessionException, UserException{
		return new ResponseEntity<TripBookingDTO>(tbService.insertTripBooking(tripBooking, uuid),HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<TripBooking> updateTrip(@RequestBody TripBooking tripBooking, @RequestParam String uuid) throws TripBookingException, CurrentUserSessionException{
		return new ResponseEntity<TripBooking>(tbService.updateTripBooking(tripBooking, uuid),HttpStatus.OK);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<TripBooking> deleteTrip(@RequestParam Integer tripBookingId,@RequestParam String uuid) throws TripBookingException, CurrentUserSessionException{
		return new ResponseEntity<TripBooking>(tbService.deleteTripBooking(tripBookingId, uuid),HttpStatus.OK);
	}
	
	@GetMapping("/allTripByCustomer")
	public ResponseEntity<List<TripBooking>> viewAllTripByCustomer(@RequestParam String uuid) throws TripBookingException, CurrentUserSessionException{
		return new ResponseEntity<List<TripBooking>>(tbService.viewAllTripsCustomer(uuid),HttpStatus.OK);
	}
	
	@GetMapping("/calculateBillofTrips")
	public ResponseEntity<String> calculateBill(@RequestParam String uuid) throws TripBookingException, CurrentUserSessionException{
		return new ResponseEntity<String>(tbService.calculateBill(uuid),HttpStatus.OK);
	}	
}