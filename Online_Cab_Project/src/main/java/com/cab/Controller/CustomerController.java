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
	
	@DeleteMapping("/delete/{customerPhoneNumber}")
	public ResponseEntity<User> customerDeletedhandler(@PathVariable("customerPhoneNumber") String customerPhoneNumber) throws UserException{
		return new ResponseEntity<User>(cService.deleteCustomer(customerPhoneNumber),HttpStatus.OK);
	}
	
	@GetMapping("/getAllCustomers")
	public ResponseEntity<List<User>> getAllCustomerHandler() throws UserException{
		return new ResponseEntity<List<User>>(cService.viewCustomers(),HttpStatus.OK);
	}
	
	@GetMapping("/getCustomer/{customerPhoneNumber}")
	public ResponseEntity<User> getCustomerhandler(@PathVariable("customerPhoneNumber") String customerPhoneNumber) throws UserException{
		return new ResponseEntity<User>(cService.viewCustomer(customerPhoneNumber),HttpStatus.OK);
	}
}
