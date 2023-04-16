package com.cab.Controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cab.Exception.CabException;
import com.cab.Exception.DriverException;
import com.cab.Model.Cab;
import com.cab.Service.CabService;



@RestController
@RequestMapping("/OnlineCabBookingApplication/cab")
public class CabController {

	@Autowired
	private CabService cabService;
	
	@PutMapping("/update")
	public ResponseEntity<Cab> updateCab(@RequestParam String licenseNo,@RequestBody Cab cab) throws DriverException{
		return new ResponseEntity<Cab>(cabService.updateCab(licenseNo, cab),HttpStatus.OK);
	}
	
	@GetMapping("/viewCabsOfType")
	public ResponseEntity<List<Cab>> viewCabsOfType(@RequestParam String carType) throws CabException{
		return new ResponseEntity<List<Cab>>(cabService.viewCabsOfType(carType),HttpStatus.OK);
	}
	
	@GetMapping("/countOfCabType")
	public ResponseEntity<Integer> countOfCabType(@RequestParam String carType) throws CabException{
		return new ResponseEntity<Integer>(cabService.countCabsOfType(carType),HttpStatus.OK);
	}
}
