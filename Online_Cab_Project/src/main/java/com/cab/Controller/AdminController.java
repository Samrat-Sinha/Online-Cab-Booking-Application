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
import com.cab.Model.User;
import com.cab.Service.AdminService;



@RestController
@RequestMapping("/OnlineCabBookingApplication/Admin")
public class AdminController {

	@Autowired
	private AdminService aService;
	
	@PostMapping("/adminRegister")
	public ResponseEntity<User> insertAdminHandler(@RequestBody User user) throws UserException{
		
		return new ResponseEntity<User>(aService.insertAdmin(user),HttpStatus.CREATED);
	}
	
	@PutMapping("/adminUpdate")
	public ResponseEntity<User> updateAdminHandler(@RequestParam String uuid, @RequestBody User user) throws UserException, CurrentUserSessionException{
		
		return new ResponseEntity<User>(aService.updateAdmin(user, uuid),HttpStatus.OK);
	}
	
	@DeleteMapping("delete")
	public ResponseEntity<User> deleteAdminHandler(@RequestParam String uuid) throws UserException, CurrentUserSessionException{
		
		return new ResponseEntity<User>(aService.deleteAdmin(uuid),HttpStatus.CREATED);
	}
	
	@GetMapping("AllTripsByCustomer")
	public ResponseEntity<List<TripBooking>> getAllTripsByAdminHandler(@RequestParam String customerPhoneNumber,@RequestParam String uuid) throws UserException, CurrentUserSessionException{
		
		return new ResponseEntity<List<TripBooking>>(aService.getAllTrips(customerPhoneNumber, uuid),HttpStatus.CREATED);
	}
	
	@GetMapping("AllTripsCabWise")
	public ResponseEntity<List<TripBooking>> getAllTripsCabWiseHandler(@RequestParam String cabType,@RequestParam String uuid) throws UserException, CurrentUserSessionException, TripBookingException{
		
		return new ResponseEntity<List<TripBooking>>(aService.getAllTripsCabWise(cabType, uuid),HttpStatus.CREATED);
	}
	
}
