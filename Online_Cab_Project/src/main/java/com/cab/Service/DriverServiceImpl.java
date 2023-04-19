package com.cab.Service;

import java.lang.StackWalker.Option;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cab.Exception.CurrentUserSessionException;
import com.cab.Exception.CustomerException;
import com.cab.Exception.DriverException;
import com.cab.Model.CurrentUserSession;
import com.cab.Model.Customer;
import com.cab.Model.Driver;
import com.cab.Repositary.AdminRepo;
import com.cab.Repositary.CurrentUserSessionRepo;
import com.cab.Repositary.CustomerRepo;
import com.cab.Repositary.DriverRepo;
import com.cab.Repositary.TripBookingRepo;

@Service
public class DriverServiceImpl implements DriverService{

	@Autowired
	private DriverRepo driverRepo;
	
	@Autowired
	private CustomerRepo customerRepo;
	
	@Autowired
	private TripBookingRepo tripbookingRepo;
		
	@Autowired
	private CurrentUserSessionRepo currRepo;
	
	
	@Override
	public Driver insertDriver(Driver driver) throws DriverException {
		
		Optional<Driver> findDriver = driverRepo.findByLicenceNo(driver.getLicenceNo());
		if(findDriver.isPresent()) {
			throw new DriverException("Driver is already registered");
		}
		else {
			if(driver.getUserRole().equalsIgnoreCase("Driver")) {
				return driverRepo.save(driver);
			}
			else {
				throw new DriverException("User is not a Driver");
			}
		}
	}

	@Override
	public Driver updateDriver(Driver driver, String uuid) throws DriverException, CurrentUserSessionException {
		
		Optional<CurrentUserSession> validCustomer = currRepo.findByUuid(uuid);
		if(validCustomer.isPresent()) {
			Optional<Driver> drv = driverRepo.findByEmail(driver.getEmail());
			if(drv.isPresent()) {
				Driver updatingdriver = drv.get();
				updatingdriver.setAddress(driver.getAddress());
				updatingdriver.setEmail(driver.getEmail());
				updatingdriver.setMobileNumber(driver.getMobileNumber());
				updatingdriver.setPassword(driver.getPassword());
				updatingdriver.setUserName(driver.getUserName());
				updatingdriver.setLicenceNo(driver.getLicenceNo());
				updatingdriver.setCab(driver.getCab());
				
				 return driverRepo.save(updatingdriver);
			}
			else {
				throw new DriverException("Driver not found with this Credentials");
			}
		}
		else {
			throw new CurrentUserSessionException("User is Not Logged In");
		}
	}

	@Override
	public Driver deleteDriver(Integer driverId, String uuid) throws DriverException, CurrentUserSessionException {
		
		Optional<CurrentUserSession> validCustomer = currRepo.findByUuid(uuid);
		if(validCustomer.isPresent()) {
			Optional<Driver> drv = driverRepo.findById(driverId);
			if(drv.isPresent()) {
				Driver updatingdriver = drv.get();
				driverRepo.delete(updatingdriver);
				return updatingdriver;
			}
			else {
				throw new DriverException("Driver not found with this Credentials");
			}
		}
		else {
			throw new CurrentUserSessionException("User is Not Logged In");
		}
	}

	@Override
	public List<Driver> viewBestDriver(String uuid) throws DriverException, CurrentUserSessionException {
		// TODO Auto-generated method stub
		List<Driver> allDrivers = driverRepo.findAll();
		List<Driver> bestDriver = new ArrayList<>();
		for(Driver d : allDrivers) {
			if(d.getRating()>=4.5) {
				bestDriver.add(d);
			}
		}
		if(bestDriver.isEmpty()) {
			throw new DriverException("No Best Driver Present");
		}
		else {
			Collections.sort(bestDriver,(a,b)-> Float.compare(b.getRating(), a.getRating()));
			return bestDriver;
		}
		
		
	}

	@Override
	public Driver viewDriver(Integer driverId,String uuid) throws DriverException, CurrentUserSessionException {
		// TODO Auto-generated method stub
		Optional<CurrentUserSession> validCustomer = currRepo.findByUuid(uuid);
		if(validCustomer.isPresent()) {
			Optional<Driver> drv = driverRepo.findById(driverId);
			if(drv.isPresent()) {
				return drv.get();
			}
			else {
				throw new DriverException("Driver not found with this Credentials");
			}
		}
		else {
			throw new CurrentUserSessionException("User is Not Logged In");
		}
	}

	
	
}
