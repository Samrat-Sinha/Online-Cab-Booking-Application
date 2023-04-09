package com.cab.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cab.Exception.CurrentUserSessionException;
import com.cab.Exception.TripBookingException;
import com.cab.Exception.UserException;
import com.cab.Model.CurrentUserSession;
import com.cab.Model.TripBooking;
import com.cab.Model.User;
import com.cab.Repositary.CurrentUserSessionRepo;
import com.cab.Repositary.TripBookingRepo;
import com.cab.Repositary.UserRepo;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private UserRepo uRepo;
	
	@Autowired
	private CurrentUserSessionRepo currRepo;
	
	@Autowired
	private TripBookingRepo tRepo;
	
	@Override
	public User insertAdmin(User user) throws UserException {
		// TODO Auto-generated method stub
		Optional<User> opt = uRepo.findByMobileNumber(user.getMobileNumber());
		if(opt.isEmpty()) {
			String role = user.getRole();
			role = role.toLowerCase();
	        if(role.equals("admin")) {
	        	return uRepo.save(user);
	        }
	        else {
	        	throw new UserException("User is not Customer");
	        }
		}
		else {
			throw new UserException("Please Recheck Admin is Already Registered!!!");
		}
	}

	@Override
	public User updateAdmin(User user, String uuid) throws UserException, CurrentUserSessionException {
		// TODO Auto-generated method stub
		Optional<CurrentUserSession> validAdmin = currRepo.findByUuid(uuid);
		CurrentUserSession curr = validAdmin.get();
		if(validAdmin.isPresent()) {
			Optional<User> admin = uRepo.findById(curr.getCurrUserId());
			if(admin.isPresent()) {
				User old = admin.get();
				old.setEmail(user.getEmail());
				old.setFirstName(user.getFirstName());
				old.setLastName(user.getLastName());
				old.setMobileNumber(user.getMobileNumber());
				old.setPassword(user.getPassword());
			    old.setTripBooking(user.getTripBooking()); 
			    return uRepo.save(user);
			}
			else {
				throw new UserException("No Admin Found with the Details Entered!!!");
			}
		}
		else {
			throw new CurrentUserSessionException("Admin Not Logged In");
		}
	}

	@Override
	public User deleteAdmin(String uuid) throws UserException, CurrentUserSessionException {
		// TODO Auto-generated method stub
		Optional<CurrentUserSession> validAdmin = currRepo.findByUuid(uuid);
		CurrentUserSession curr = validAdmin.get();
		if(validAdmin.isPresent()) {
			Optional<User> admin = uRepo.findById(curr.getCurrUserId());
			if(admin.isPresent()) {
				User delAdmin = admin.get();
				uRepo.delete(delAdmin);
				return delAdmin;
			}
			else {
				throw new UserException("No Admin Found with the Details Entered!!!");
			}
		}
		else {
			throw new CurrentUserSessionException("Admin Not Logged In");
		}
	}

	@Override
	public List<TripBooking> getAllTrips(String customerPhoneNumber, String uuid)
			throws UserException, CurrentUserSessionException {
		// TODO Auto-generated method stub
		Optional<CurrentUserSession> validAdmin = currRepo.findByUuid(uuid);
		CurrentUserSession curr = validAdmin.get();
		if(validAdmin.isPresent()) {
			Optional<User> customer = uRepo.findByMobileNumber(customerPhoneNumber);
			if(customer.isPresent()) {
				User cus = customer.get();
				List<TripBooking> allTrip = cus.getTripBooking();
				if(allTrip.isEmpty()) {
					throw new UserException("No Trip is Booked till now by the Customer");
				}
				else {
					return allTrip;
				}
			}
			else {
				throw new UserException("Customer Not Found with this Details!!!");
			}
		}
		else {
			throw new CurrentUserSessionException("Admin Not Logged In");
		}
	}

	@Override
	public List<TripBooking> getAllTripsCabWise(String cabType, String uuid)
			throws UserException, CurrentUserSessionException,TripBookingException {
		// TODO Auto-generated method stub
		Optional<CurrentUserSession> validAdmin = currRepo.findByUuid(uuid);
		CurrentUserSession curr = validAdmin.get();
		if(validAdmin.isPresent()) {
			List<TripBooking> allTrip = tRepo.findAll();
			List<TripBooking> cabWise = new ArrayList<>();
			for(TripBooking tb : allTrip) {
				if(tb.getDriver().getCab().getCarType().equals(cabType)) {
					cabWise.add(tb);
				}
			}
			if(cabWise.isEmpty()) {
				throw new TripBookingException("No Trip is been Booked bt this cab Type");
			}
			else {
				return cabWise;
			}
		}
		else {
			throw new CurrentUserSessionException("Admin Not Logged In");
		}
	}
	
	@Override
	public List<User> viewCustomers(String uuid) throws UserException, CurrentUserSessionException {
		// TODO Auto-generated method stub
		Optional<CurrentUserSession> validAdmin = currRepo.findByUuid(uuid);
		CurrentUserSession curr = validAdmin.get();
		if(validAdmin.isPresent()) {
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
		else {
			throw new CurrentUserSessionException("Admin Not Logged In");
		}
	}

	@Override
	public User viewCustomer(String customerPhoneNumber,String uuid) throws UserException, CurrentUserSessionException {
		// TODO Auto-generated method stub
		Optional<CurrentUserSession> validAdmin = currRepo.findByUuid(uuid);
		CurrentUserSession curr = validAdmin.get();
		if(validAdmin.isPresent()) {
			Optional<User> opt = uRepo.findByMobileNumber(customerPhoneNumber);
			if(opt.isEmpty()) {
				throw new UserException("No Customer Found with the Details Entered!!!");
			}
			else {
				return opt.get();
			}
		}
		else {
			throw new CurrentUserSessionException("Admin Not Logged In");
		}	
	}

	

}