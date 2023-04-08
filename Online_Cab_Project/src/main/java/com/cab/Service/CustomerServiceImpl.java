package com.cab.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cab.Exception.CurrentUserSessionException;

import com.cab.Exception.UserException;
import com.cab.Model.CurrentUserSession;

import com.cab.Model.User;
import com.cab.Repositary.CurrentUserSessionRepo;
import com.cab.Repositary.UserRepo;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private UserRepo uRepo;
	
	@Autowired
	private CurrentUserSessionRepo currRepo;
	
	@Override
	public User insertCustomer(User user) throws UserException {
		// TODO Auto-generated method stub
		Optional<User> opt = uRepo.findByMobileNumber(user.getMobileNumber());
		if(opt.isEmpty()) {
			String role = user.getRole();
			role = role.toLowerCase();
	        if(role.equals("customer")) {
	        	return uRepo.save(user);
	        }
	        else {
	        	throw new UserException("User is not Customer");
	        }
		}
		else {
			throw new UserException("Please Recheck Customer is Already Registered!!!");
		}
	}

	@Override
	public User updateCustomer(User user, String uuid) throws UserException,CurrentUserSessionException {
		// TODO Auto-generated method stub
		Optional<CurrentUserSession> validAdmin = currRepo.findByUuid(uuid);
		CurrentUserSession curr = validAdmin.get();
		if(validAdmin.isPresent()) {
			Optional<User> opt = uRepo.findById(curr.getCurrUserId());
			User old = opt.get();
			old.setEmail(user.getEmail());
			old.setFirstName(user.getFirstName());
			old.setLastName(user.getLastName());
			old.setMobileNumber(user.getMobileNumber());
			old.setPassword(user.getPassword());
		    old.setTripBooking(user.getTripBooking()); 
		    return uRepo.save(user);
		}
		else {
			throw new CurrentUserSessionException("Admin Not Logged In");
		}
	}

	@Override
	public User deleteCustomer(String customerPhoneNumber,String uuid) throws UserException,CurrentUserSessionException {
		// TODO Auto-generated method stub
		Optional<CurrentUserSession> validAdmin = currRepo.findByUuid(uuid);
		CurrentUserSession curr = validAdmin.get();
		if(validAdmin.isPresent()) {
			Optional<User> opt = uRepo.findById(curr.getCurrUserId());
			User customer = opt.get();
			uRepo.delete(customer);
			return customer;
		}
		else {
			throw new CurrentUserSessionException("Admin Not Logged In");
		}
	}

	
	
	
	
	
}
