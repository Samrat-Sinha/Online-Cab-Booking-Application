package com.cab.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cab.Exception.CurrentUserSessionException;
import com.cab.Exception.TripBookingException;
import com.cab.Exception.UserException;
import com.cab.Model.CurrentUserSession;
import com.cab.Model.Driver;
import com.cab.Model.TripBooking;
import com.cab.Model.User;
import com.cab.Repositary.CurrentUserSessionRepo;
import com.cab.Repositary.DriverRepo;
import com.cab.Repositary.TripBookingRepo;
import com.cab.Repositary.UserRepo;

@Service
public class TripBookingServiceImpl implements TripBookingService{

	@Autowired
	private UserRepo uRepo;
	
	@Autowired
	private CurrentUserSessionRepo currRepo;
	
	@Autowired
	private TripBookingRepo tRepo;
	
	@Autowired
	private DriverRepo dRepo;
	
	@Override
	public TripBooking insertTripBooking(TripBooking tripBooking, String uuid)
	        throws TripBookingException, CurrentUserSessionException, UserException {
	    Optional<CurrentUserSession> validAdmin = currRepo.findByUuid(uuid);
	    CurrentUserSession curr = validAdmin.get();
	    if (validAdmin.isPresent()) {
	        Optional<User> cust = uRepo.findById(curr.getCurrUserId());
	        User customer = cust.get();
	        List<TripBooking> addTrip = customer.getTripBooking();

	        boolean tripAlreadyBooked = tRepo.existsByDateTimeAndLocation(tripBooking.getFromDateTime(),
	                tripBooking.getToDateTime(), tripBooking.getFromLocation(), tripBooking.getToLocation());
	        if (tripAlreadyBooked) {
	            throw new TripBookingException("Trip already booked by customer");
	        } else {
	          
	            Driver driver = dRepo.findFirstByCabCarTypeAndCurrentLocation(tripBooking.getCarType(),
	                    tripBooking.getFromLocation())
	                    .orElseThrow(() -> new TripBookingException("No available drivers found"));

	          
	            tripBooking.setDriver(driver);

	          
	            addTrip.add(tripBooking);
	            customer.setTripBooking(addTrip);
	            TripBooking tsave = tRepo.save(tripBooking);
	            tsave.setUser(customer);
	            tRepo.save(tsave);
	            return tsave;
	        }
	    } else {
	        throw new CurrentUserSessionException("Customer Not Logged In");
	    }
	}


}