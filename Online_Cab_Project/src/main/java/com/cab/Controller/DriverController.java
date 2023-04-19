package com.cab.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
import com.cab.Exception.DriverException;
import com.cab.Model.Driver;
import com.cab.Service.DriverService;

@RestController
@RequestMapping("/driver")
public class DriverController {

	@Autowired
	private DriverService driverService;
	
	@PostMapping("/register")
	public ResponseEntity<Driver> register(@RequestBody Driver driver) throws DriverException{
		return new ResponseEntity<Driver>(driverService.insertDriver(driver),HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Driver> update(@RequestBody Driver driver, @RequestParam("uuid") String uuid) throws DriverException, CurrentUserSessionException{
		return new ResponseEntity<Driver>(driverService.updateDriver(driver, uuid),HttpStatus.OK);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<Driver> delete(@RequestParam("driverId") Integer driverId,@RequestParam("uuid") String uuid) throws DriverException, CurrentUserSessionException{
		return new ResponseEntity<Driver>(driverService.deleteDriver(driverId, uuid),HttpStatus.OK);
	}
	
	@GetMapping("/viewBestDriver")
	public ResponseEntity<List<Driver>> viewBestDriver(@RequestParam("uuid") String uuid) throws DriverException, CurrentUserSessionException{
		return new ResponseEntity<List<Driver>>(driverService.viewBestDriver(uuid),HttpStatus.OK);
	}
	
	@GetMapping("viewDriver")
	public ResponseEntity<Driver> viewDriver(@RequestParam("driverId") Integer driverId,@RequestParam("uuid")String uuid) throws DriverException, CurrentUserSessionException{
		return new ResponseEntity<Driver>(driverService.viewDriver(driverId, uuid),HttpStatus.OK);
	}
}	

