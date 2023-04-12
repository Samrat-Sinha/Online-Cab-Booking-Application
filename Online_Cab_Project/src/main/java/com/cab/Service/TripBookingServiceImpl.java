package com.cab.Service;
import com.cab.Model.DriverAvalability;

import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cab.Exception.CurrentUserSessionException;
import com.cab.Exception.TripBookingException;
import com.cab.Exception.UserException;
import com.cab.Model.Cab;
import com.cab.Model.CurrentUserSession;
import com.cab.Model.Driver;
import com.cab.Model.DriverAvalability;
import com.cab.Model.TripBooking;
import com.cab.Model.TripBookingDTO;
import com.cab.Model.User;
import com.cab.Repositary.CabRepo;
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
	
	@Autowired
	private CabRepo cabRepo;

	@Override
	public TripBookingDTO insertTripBooking(TripBooking tripBooking, String uuid) throws TripBookingException, CurrentUserSessionException, UserException {
		// TODO Auto-generated method stub
		Optional<CurrentUserSession> validAdmin = currRepo.findByUuid(uuid);
		CurrentUserSession curr = validAdmin.get();
		if(validAdmin.isPresent()) {
			Optional<User> opt = uRepo.findById(curr.getCurrUserId());
			User customer = opt.get();
			List<TripBooking> allTrip = customer.getTripBooking();
			boolean hasTrip = false;
			for(TripBooking tb : allTrip) {
				if(tb.getFromDate().equals(tripBooking.getFromDate()) &&
						tb.getToDate().equals(tripBooking.getToDate()) && 
						 tb.getFromLocation().equals(tripBooking.getFromLocation()) && 
						 tb.getToLocation().equals(tripBooking.getToLocation()) ) {
					      hasTrip = true;
					      break;
				}
			}
			
			if(hasTrip) {
				throw new TripBookingException("Trip is already Booked!!!!");
			}
			else {
				
				Driver dr = dRepo.findByCabCarTypeAndCurrentLocationAndAvalable(tripBooking.getCarType(),
		                tripBooking.getFromLocation(), DriverAvalability.AVAILABLE)
		                .orElseThrow(() -> new TripBookingException("No available drivers found"));

				
				
			   if(EnumSet.of(DriverAvalability.AVAILABLE).contains(dr.getAvalable())) {
				    Cab cab = dr.getCab();
					allTrip.add(tripBooking);
					customer.setTripBooking(allTrip);
					TripBooking saveTrip = tRepo.save(tripBooking);
					saveTrip.setUser(customer);
					dr.setAvalable(DriverAvalability.NOT_AVAILABLE);
					saveTrip.setDriver(dr);
					tRepo.save(saveTrip);
					
					TripBookingDTO newTrip = new TripBookingDTO();
					newTrip.setFirstName(customer.getFirstName());
					newTrip.setLastName(customer.getLastName());
					newTrip.setTripBookingId(saveTrip.getTripBookingId());
					newTrip.setFromLocation(saveTrip.getFromLocation());
					newTrip.setToLocation(saveTrip.getToLocation());
					newTrip.setFromDate(saveTrip.getFromDate());
					newTrip.setToDate(saveTrip.getToDate());
					newTrip.setDriverFirstName(dr.getDriverFirstName());
					newTrip.setDriverLastName(dr.getDriverLastName());
					newTrip.setLicenseNo(dr.getLicenseNo());
					newTrip.setCarType(cab.getCarType());
					newTrip.setCarName(cab.getCarName());
					newTrip.setCarNumber(cab.getCarNumber());
					newTrip.setRating(dr.getRating());
					
					float tripBill = cab.getPerKmRate() * tripBooking.getDistanceInKm();
					
					newTrip.setBill(tripBill);
					
					return newTrip; 
			   }
			   else {
				   throw new TripBookingException("No Driver Avalable Currently!!!");
			   }
				
				
				
			}
		 }
		else {
			throw new CurrentUserSessionException("Admin Not Logged In");
		}
	}
	
	

}