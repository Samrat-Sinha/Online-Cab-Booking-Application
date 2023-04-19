package com.cab.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cab.Exception.AdminException;
import com.cab.Exception.CurrentUserSessionException;
import com.cab.Exception.CustomerException;
import com.cab.Exception.TripBookingException;
import com.cab.Model.Admin;
import com.cab.Model.TripBooking;
import com.cab.Service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	
	@PostMapping("/register")
	public ResponseEntity<Admin> registerAdmin(@RequestBody Admin admin) throws AdminException{
		return new ResponseEntity<Admin>(adminService.insertAdmin(admin),HttpStatus.CREATED);
	}
	
	@PutMapping("/Update")
	public ResponseEntity<Admin> updateAdminHandler(@RequestBody Admin admin,@RequestParam String uuid) throws AdminException, CurrentUserSessionException{
		return new ResponseEntity<Admin>(adminService.updateAdmin(admin, uuid),HttpStatus.OK);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<Admin> deleteAdmin(@RequestParam("adminId") Integer adminId,@RequestParam("uuid")  String uuid) throws AdminException, CurrentUserSessionException{
		return new ResponseEntity<Admin>(adminService.deleteAdmin(adminId, uuid),HttpStatus.OK);
	}
	
	@GetMapping("/getAllTrips")
	public ResponseEntity<List<TripBooking>> getAllTrips(@RequestParam("uuid")  String uuid) throws AdminException, TripBookingException, CurrentUserSessionException{
		return new ResponseEntity<List<TripBooking>>(adminService.getAllTrips(uuid),HttpStatus.OK);
	}
	
	@GetMapping("/getTripsCabwise/{carType}")
	public ResponseEntity<List<TripBooking>> getTripsCabwise(@PathVariable("carType") String carType,@RequestParam("uuid")  String uuid) throws TripBookingException, CurrentUserSessionException{
		return new ResponseEntity<List<TripBooking>>(adminService.getTripsCabwise(carType, uuid),HttpStatus.OK);
	}
	
	@GetMapping("/getTripsCustomerwise")
	public ResponseEntity<List<TripBooking>> getTripsCustomerwise(@RequestParam("customerId") Integer customerId,@RequestParam("uuid")  String uuid) throws TripBookingException, CustomerException, CurrentUserSessionException{
		return new ResponseEntity<List<TripBooking>>(adminService.getTripsCustomerwise(customerId, uuid),HttpStatus.OK);
	}
	
	@GetMapping("/getAllTripsForDays/{fromDateTime}/{toDateTime}")
	public ResponseEntity<List<TripBooking>> getAllTripsForDays(@RequestParam("customerId") Integer customerId,@PathVariable("fromDateTime") String fromDateTime,@PathVariable("toDateTime") String toDateTime ,@RequestParam("uuid")  String uuid) throws TripBookingException, CustomerException, CurrentUserSessionException{
		return new ResponseEntity<List<TripBooking>>(adminService.getAllTripsForDays(customerId, fromDateTime, toDateTime, uuid),HttpStatus.OK);
	}
}  