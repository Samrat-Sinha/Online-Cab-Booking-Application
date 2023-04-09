package com.cab.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
}
