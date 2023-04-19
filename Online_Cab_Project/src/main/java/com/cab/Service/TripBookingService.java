package com.cab.Service;

import java.util.List;

import com.cab.Exception.CabException;
import com.cab.Exception.CurrentUserSessionException;
import com.cab.Exception.TripBookingException;
import com.cab.Model.Cab;
import com.cab.Model.TripBooking;
import com.cab.Model.TripBookingDTO;

public interface TripBookingService {

	List<Cab> searchByLocation(String pickUpLocation,String uuid)throws TripBookingException,CurrentUserSessionException;
	
	TripBooking BookRequest(Integer cabId,TripBooking tripBooking,String uuid) throws TripBookingException,CabException,CurrentUserSessionException;
	
	TripBooking AssignDriverByAdmin(Integer TripBookingId,String uuid)throws TripBookingException,CabException,CurrentUserSessionException;
	
	TripBookingDTO viewBookingById(Integer TripBookingId,String uuid )throws TripBookingException,CabException,CurrentUserSessionException;
	
	String MarkTripAsCompleted(Integer TripBookingId,String uuid)throws TripBookingException,CurrentUserSessionException;;
}
