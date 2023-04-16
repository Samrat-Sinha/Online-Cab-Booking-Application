package com.cab.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
	public TripBookingDTO insertTripBooking(TripBooking tripBooking, String uuid)
			throws TripBookingException, CurrentUserSessionException, UserException {
		// TODO Auto-generated method stub
		Optional<CurrentUserSession> validLog = currRepo.findByUuid(uuid);
		CurrentUserSession curr = validLog.get();
		if(validLog.isPresent()) {
			Optional<User> opt = uRepo.findById(curr.getCurrUserId());
			User customer  = opt.get();
			List<TripBooking> allTrip = customer.getTripBooking();
			List<TripBooking> bookedTrips = tRepo.findByFromDateTimeBetweenAndUser(tripBooking.getFromDateTime(),tripBooking.getToDateTime(),customer);
			if(!bookedTrips.isEmpty()) {
			    throw new TripBookingException("You have already booked a trip for this time slot.");
			}
			else {
				
				Driver driver = dRepo.findFirstByCabCarTypeAndCurrentLocationAndOnAnotherRide(tripBooking.getCarType(),
	                    tripBooking.getFromLocation(), false)
	                    .orElseThrow(() -> new TripBookingException("No available drivers found"));

				driver.setOnAnotherRide(true);
				tripBooking.setDriver(driver);
				allTrip.add(tripBooking);
	            customer.setTripBooking(allTrip);
	            TripBooking tsave = tRepo.save(tripBooking);
	            tsave.setUser(customer);
	            tRepo.save(tsave);
				TripBookingDTO dto = new TripBookingDTO();
				dto.setFirstName(customer.getFirstName());
				dto.setLastName(customer.getLastName());
				dto.setFromLocation(tsave.getFromLocation());
				dto.setToLocation(tsave.getToLocation());
				dto.setTripBookingId(tsave.getTripBookingId());
				
				LocalDateTime fromDT = tsave.getFromDateTime();
				LocalDateTime toDT = tsave.getToDateTime();
				
				DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				String fromDate = fromDT.format(dateFormatter);
				String toDate = toDT.format(dateFormatter);
				
				DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
				
				String fromTime = fromDT.format(timeFormatter);
				String toTime = toDT.format(timeFormatter);
				
				dto.setFromDate(fromDate);
				dto.setToDate(toDate);
				
				dto.setFromTime(fromTime);
				dto.setToTime(toTime);
				

				
				
				
				dto.setDriverFirstName(driver.getDriverFirstName());
				dto.setDriverLastName(driver.getDriverLastName());
				dto.setLicenseNo(driver.getLicenseNo());
				dto.setCarType(driver.getCab().getCarType());
				dto.setCarName(driver.getCab().getCarName());
				dto.setCarNumber(driver.getCab().getCarNumber());
				dto.setRating(driver.getRating());
				float ans = driver.getCab().getPerKmRate() * tsave.getDistanceInKm();
				dto.setBill(ans);
				return dto;
			}
		}
		else {
			throw new CurrentUserSessionException("User Not Logged In");
		}
	}

	@Override
	public TripBooking updateTripBooking(TripBooking tripBooking,String uuid)
			throws TripBookingException, CurrentUserSessionException {
		// TODO Auto-generated method stub
		Optional<CurrentUserSession> validLog = currRepo.findByUuid(uuid);
		CurrentUserSession curr = validLog.get();
		if(validLog.isPresent()) {
			Optional<User> opt = uRepo.findById(curr.getCurrUserId());
			User customer  = opt.get();
			List<TripBooking> allTrips = customer.getTripBooking();
			TripBooking ans = new TripBooking();
			boolean change = false;
			for(TripBooking tb : allTrips) {
				if(tb.getTripBookingId()== tripBooking.getTripBookingId()) {
					ans = tb;
					change = true;
				}
			}
			if(allTrips.isEmpty()) {
				throw new TripBookingException("No Trip is booked Till now");
			}
			else if(change==false) {
		    	throw new TripBookingException("No Trip is deleted so maybe the tripId is wrong");
		    }
			else {
				allTrips.remove(ans);
				ans.setCarType(tripBooking.getCarType());
				ans.setDistanceInKm(tripBooking.getDistanceInKm());
				ans.setFromDateTime(tripBooking.getFromDateTime());
				ans.setFromLocation(tripBooking.getFromLocation());
				ans.setToDateTime(tripBooking.getToDateTime());
				ans.setToLocation(tripBooking.getToLocation());
				Driver olddriver = ans.getDriver();
				olddriver.setOnAnotherRide(false);
				dRepo.save(olddriver);
				Driver driver = dRepo.findFirstByCabCarTypeAndCurrentLocationAndOnAnotherRide(tripBooking.getCarType(),
	                    tripBooking.getFromLocation(), false)
	                    .orElseThrow(() -> new TripBookingException("No available drivers found"));

				driver.setOnAnotherRide(true);
				tripBooking.setDriver(driver);
				
				allTrips.add(ans);
			    customer.setTripBooking(allTrips);
			    tRepo.save(ans);
			    uRepo.save(customer);
				return ans;
				
			}
		}
		else {
			throw new CurrentUserSessionException("User Not Logged In");
		}
	}

	@Override
	public TripBooking deleteTripBooking(Integer tripBookingId,String uuid)
			throws TripBookingException, CurrentUserSessionException{
		// TODO Auto-generated method stub
		Optional<CurrentUserSession> validLog = currRepo.findByUuid(uuid);
		CurrentUserSession curr = validLog.get();
		if(validLog.isPresent()) {
			Optional<User> opt = uRepo.findById(curr.getCurrUserId());
			User customer  = opt.get();
			List<TripBooking> allTripBookings = customer.getTripBooking();
		    TripBooking ans = new TripBooking();
		    boolean change = false;
			for(TripBooking tb : allTripBookings) {
		    	if(tb.getTripBookingId() == tripBookingId) {
		    		ans = tb;
		    		change = true;
		    	}
		    }
			allTripBookings.remove(ans);
			customer.setTripBooking(allTripBookings);
			uRepo.save(customer);
			
			
		    if(allTripBookings.isEmpty()) {
		    	throw new TripBookingException("No Trip is booked Till now");
		    }
			else if(change==false) {
		    	throw new TripBookingException("No Trip is deleted so maybe the tripId is wrong");
		    }
		    else {
		    	return ans; 
		    }
		}
		else {
			throw new CurrentUserSessionException("User Not Logged In");
		}
	}

	@Override
	public List<TripBooking> viewAllTripsCustomer(String uuid)
			throws TripBookingException, CurrentUserSessionException {
		// TODO Auto-generated method stub
		Optional<CurrentUserSession> validLog = currRepo.findByUuid(uuid);
		CurrentUserSession curr = validLog.get();
		if(validLog.isPresent()) {
			Optional<User> opt = uRepo.findById(curr.getCurrUserId());
			User customer  = opt.get();
			List<TripBooking> allTripBookings = customer.getTripBooking();
		    if(allTripBookings.isEmpty()) {
		    	throw new TripBookingException("No Trip is Booked By the Customer");
		    }
		    else {
		    	return allTripBookings;
		    }
		}
		else {
			throw new CurrentUserSessionException("User Not Logged In");
		}
	}

	@Override
	public String calculateBill(String uuid) throws TripBookingException, CurrentUserSessionException{
		// TODO Auto-generated method stub
		Optional<CurrentUserSession> validLog = currRepo.findByUuid(uuid);
		CurrentUserSession curr = validLog.get();
		if(validLog.isPresent()) {
			Optional<User> opt = uRepo.findById(curr.getCurrUserId());
			User customer  = opt.get();
		    float totalBill = 0;
		    List<TripBooking> allTripBookings = customer.getTripBooking();
		    for(TripBooking tb : allTripBookings) {
		    	totalBill += tb.getDistanceInKm() * tb.getDriver().getCab().getPerKmRate();
		    }
		    if(totalBill==0) {
		    	throw new TripBookingException("No Trip is Booked By the Customer");
		    }
		    else {
		    	return "The Total Bill by the Customer " + customer.getFirstName() + " " + customer.getLastName() + " is Rs " + totalBill;
		    }
		}
		else {
			throw new CurrentUserSessionException("User Not Logged In");
		}
	}

	
	
	

}