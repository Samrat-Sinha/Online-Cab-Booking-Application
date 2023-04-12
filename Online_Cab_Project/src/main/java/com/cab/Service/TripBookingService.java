package com.cab.Service;

import com.cab.Exception.CurrentUserSessionException;
import com.cab.Exception.TripBookingException;
import com.cab.Exception.UserException;
import com.cab.Model.TripBooking;
import com.cab.Model.TripBookingDTO;

public interface TripBookingService {

	public TripBookingDTO insertTripBooking(TripBooking tripBooking,String uuid)throws TripBookingException,CurrentUserSessionException,UserException;
	
}