package com.cab.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cab.Exception.UserException;
import com.cab.Model.User;
import com.cab.Service.CustomerService;

@RestController
@RequestMapping("/OnlineCabBookingApplication/Customer")
public class CustomerController {

	@Autowired
	private CustomerService cService;
	
	@PostMapping("/Register")
	public ResponseEntity<User> customerRegisterHandler(@RequestBody User user) throws UserException{
		return new ResponseEntity<User>(cService.insertCustomer(user),HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<User> customerUpdateHandler(@RequestBody User user) throws UserException{
		return new ResponseEntity<User>(cService.updateCustomer(user),HttpStatus.OK);
	}
}
