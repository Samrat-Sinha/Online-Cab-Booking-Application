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

import com.cab.Exception.DriverException;
import com.cab.Model.Driver;
import com.cab.Service.DriverService;

@RestController
@RequestMapping("/OnlineCabBookingApplication/driver")
public class DriverController {

	@Autowired
	private DriverService dService;
	
	@PostMapping("/register")
	public ResponseEntity<Driver> registerHandler(@RequestBody Driver driver) throws DriverException{
		return new ResponseEntity<Driver>(dService.insertDriver(driver),HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Driver> updateHandler(@RequestBody Driver driver) throws DriverException{
		return new ResponseEntity<Driver>(dService.updateDriver(driver),HttpStatus.OK);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<Driver> deleteDriver(@RequestParam String LicenseNo) throws DriverException{
		return new ResponseEntity<Driver>(dService.deleteDriver(LicenseNo),HttpStatus.OK);
	}
	
	@GetMapping("/bestDrivers")
	public ResponseEntity<List<Driver>> viewBestDrivers()throws DriverException{
		return new ResponseEntity<List<Driver>>(dService.viewBestDrivers(),HttpStatus.OK);
	}
	
	@GetMapping("/getDriver")
	public ResponseEntity<Driver> getDriver(@RequestParam String LicenseNo) throws DriverException{
		return new ResponseEntity<Driver>(dService.viewDriver(LicenseNo),HttpStatus.OK); 
	}
	
}
