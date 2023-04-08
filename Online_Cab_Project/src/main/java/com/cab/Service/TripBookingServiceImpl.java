package com.cab.Service;

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
public class TripBookingServiceImpl implements TripBookingService{

	@Autowired
	private UserRepo uRepo;
	
	@Autowired
	private CurrentUserSessionRepo currRepo;
	
	@Autowired
	private TripBookingRepo tRepo;
	
	@Override
	public TripBooking insertTripBooking(TripBooking tripBooking,String uuid)
			throws TripBookingException, CurrentUserSessionException, UserException {
		// TODO Auto-generated method stub
		Optional<CurrentUserSession> validAdmin = currRepo.findByUuid(uuid);
		CurrentUserSession curr = validAdmin.get();
		if(validAdmin.isPresent()) {
			Optional<User> cust = uRepo.findById(curr.getCurrUserId());
			User customer = cust.get();
			List<TripBooking> addTrip = customer.getTripBooking();
			if(addTrip.isEmpty()) {
				addTrip.add(tripBooking);
				return tripBooking;
			}
			else {
				Optional<TripBooking> trp = tRepo.findById(tripBooking.getTripBookingId());
				if(trp.isEmpty()) {
					addTrip.add(tripBooking);
					return tripBooking;
				}
				else {
					throw new UserException("User Already booked this trip!!!");
				}
			}
		}
		else {
			throw new CurrentUserSessionException("Admin Not Logged In");
		}
	}

}
