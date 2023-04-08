package com.cab.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cab.Exception.UserException;
import com.cab.Model.LoginDTO;
import com.cab.Model.User;
import com.cab.Repositary.UserRepo;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private UserRepo uRepo;
	
	@Override
	public User insertCustomer(User user) throws UserException {
		// TODO Auto-generated method stub
		Optional<User> opt = uRepo.findByMobileNumber(user.getMobileNumber());
		if(opt.isEmpty()) {
			return uRepo.save(user);
		}
		else {
			throw new UserException("Please Recheck Customer is Already Registered!!!");
		}
	}

	@Override
	public User updateCustomer(User user) throws UserException {
		// TODO Auto-generated method stub
		Optional<User> opt = uRepo.findByMobileNumber(user.getMobileNumber());
		if(opt.isEmpty()) {
			throw new UserException("No Customer Found with the Details Entered!!!");
		}
		else {
			User old = opt.get();
			old.setEmail(user.getEmail());
			old.setFirstName(user.getFirstName());
			old.setLastName(user.getLastName());
			old.setMobileNumber(user.getMobileNumber());
			old.setPassword(user.getPassword());
		    old.setTripBooking(user.getTripBooking()); 
		    return uRepo.save(user);
		}
	}

	@Override
	public User deleteCustomer(String customerPhoneNumber) throws UserException {
		// TODO Auto-generated method stub
		Optional<User> opt = uRepo.findByMobileNumber(customerPhoneNumber);
		if(opt.isEmpty()) {
			throw new UserException("No Customer Found with the Details Entered!!!");
		}
		else {
			User customer = opt.get();
			uRepo.delete(customer);
			return customer;
		}
	}

	@Override
	public List<User> viewCustomers() throws UserException {
		// TODO Auto-generated method stub
		List<User> alluser = uRepo.findAll();
		List<User> customers = new ArrayList<>();
		for(User u : alluser) {
			String check = u.getRole();
			check = check.toLowerCase();
			if(check.equals("customer")) {
				customers.add(u);
			}
		}
		if(customers.isEmpty()) {
			throw new UserException("No Customer Present in the Application!!!");
		}
		else {
			return customers;
		}
	}

	@Override
	public User viewCustomer(String customerPhoneNumber) throws UserException {
		// TODO Auto-generated method stub
		Optional<User> opt = uRepo.findByMobileNumber(customerPhoneNumber);
		if(opt.isEmpty()) {
			throw new UserException("No Customer Found with the Details Entered!!!");
		}
		else {
			return opt.get();
		}
	}

	
	
	
	
}
