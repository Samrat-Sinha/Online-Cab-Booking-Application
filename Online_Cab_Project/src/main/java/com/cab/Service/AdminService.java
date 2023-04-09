package com.cab.Service;

import java.util.List;

import com.cab.Exception.CurrentUserSessionException;
import com.cab.Exception.TripBookingException;
import com.cab.Exception.UserException;
import com.cab.Model.TripBooking;
import com.cab.Model.User;

public interface AdminService {

	public User insertAdmin(User user)throws UserException;
	
	public User updateAdmin(User user, String uuid)throws UserException,CurrentUserSessionException;
	
	public User deleteAdmin(String uuid)throws UserException,CurrentUserSessionException;
	
	public List<TripBooking> getAllTrips(String customerPhoneNumber,String uuid)throws UserException,CurrentUserSessionException;
	
	public List<TripBooking> getAllTripsCabWise(String cabType,String uuid)throws UserException,CurrentUserSessionException,TripBookingException;
	
    public List<User> viewCustomers(String uuid)throws UserException,CurrentUserSessionException;
	
	public User viewCustomer(String customerPhoneNumber,String uuid)throws UserException,CurrentUserSessionException;
}