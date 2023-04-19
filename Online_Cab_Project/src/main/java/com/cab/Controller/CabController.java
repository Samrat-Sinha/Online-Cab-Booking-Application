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

import com.cab.Exception.CabException;
import com.cab.Exception.CurrentUserSessionException;
import com.cab.Model.Cab;
import com.cab.Service.CabService;

@RestController
@RequestMapping("/cab")
public class CabController {
	
	@Autowired
	private CabService cabService;
	
	@PostMapping("/register")
	public ResponseEntity<Cab> register(@RequestBody Cab cab) throws CabException{
		return new ResponseEntity<Cab>(cabService.insertCab(cab),HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Cab> update(@RequestBody Cab cab,@RequestParam("uuid") String uuid) throws CabException, CurrentUserSessionException{
		return new ResponseEntity<Cab>(cabService.updateCab(cab, uuid),HttpStatus.OK);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<Cab> deleteCab(@RequestParam("cabId") Integer cabId,@RequestParam("uuid") String uuid) throws CabException, CurrentUserSessionException{
		return new ResponseEntity<Cab>(cabService.deleteCab(cabId, uuid),HttpStatus.OK);
	}
	
	@GetMapping("/viewCabsOfType/{carType}")
	public ResponseEntity<List<Cab>> viewCabsOfType(@PathVariable("carType") String carType,@RequestParam("uuid") String uuid) throws CabException, CurrentUserSessionException{
		return new ResponseEntity<List<Cab>>(cabService.viewCabsOfType(carType, uuid),HttpStatus.OK);
	}
	
	@GetMapping("/countCabsOfType/{carType}")
	public ResponseEntity<Integer> countCabsOfType(@PathVariable("carType") String carType,@RequestParam("uuid") String uuid) throws CabException, CurrentUserSessionException{
		return new ResponseEntity<Integer>(cabService.countCabsOfType(carType, uuid),HttpStatus.OK);
	}
}


	
