package com.cab.Service;

import java.util.List;

import com.cab.Exception.CurrentUserSessionException;
import com.cab.Exception.TripBookingException;
import com.cab.Exception.UserException;
import com.cab.Model.TripBooking;
import com.cab.Model.TripBookingDTO;

public interface TripBookingService {

	public TripBookingDTO insertTripBooking(TripBooking tripBooking,String uuid)throws TripBookingException,CurrentUserSessionException,UserException;
	
    public TripBooking updateTripBooking(TripBooking tripBooking,String uuid)throws TripBookingException,CurrentUserSessionException;
	
    public TripBooking deleteTripBooking(Integer tripBookingId,String uuid)throws TripBookingException,CurrentUserSessionException;
	
    public List<TripBooking> viewAllTripsCustomer(String uuid)throws TripBookingException,CurrentUserSessionException;
    
    public String calculateBill(String uuid)throws TripBookingException,CurrentUserSessionException;
    
    
}