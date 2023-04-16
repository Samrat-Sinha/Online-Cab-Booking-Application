package com.cab.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cab.Exception.DriverException;
import com.cab.Model.Driver;
import com.cab.Repositary.DriverRepo;
@Service
public class DriverServiceImpl implements DriverService{

	@Autowired
	private DriverRepo dRepo; 
	
	
	@Override
	public Driver insertDriver(Driver driver) throws DriverException {
	    Optional<Driver> existingDriver = dRepo.findByLicenseNo(driver.getLicenseNo());
	    if (existingDriver.isPresent()) {
	        
	        throw new DriverException("Driver is Already Registered");
	        
	    } else {
	        return dRepo.save(driver);
	    }
	}


	@Override
	public Driver updateDriver(Driver driver) throws DriverException {
		// TODO Auto-generated method stub
		Optional<Driver> existingDriver = dRepo.findByLicenseNo(driver.getLicenseNo());
		if(existingDriver.isPresent()) {
			Driver oldData = existingDriver.get();
			oldData.setCurrentLocation(driver.getCurrentLocation());
			oldData.setDriverFirstName(driver.getDriverFirstName());
			oldData.setDriverLastName(driver.getDriverLastName());
			oldData.setCab(driver.getCab());
            dRepo.save(oldData);
            return oldData;
		}
		else {
			throw new DriverException("Driver is not Registered");
		}
	}


	@Override
	public Driver deleteDriver(String LicenseNo) throws DriverException {
		// TODO Auto-generated method stub
		Optional<Driver> existingDriver = dRepo.findByLicenseNo(LicenseNo);
		if(existingDriver.isPresent()) {
			Driver del = existingDriver.get();
			dRepo.delete(del);
			return del;
			
		}
		else {
			throw new DriverException("Driver is not Registered");
		}
	}


	@Override
	public List<Driver> viewBestDrivers() throws DriverException {
		// TODO Auto-generated method stub
		List<Driver> allDriver = dRepo.findAll();
		List<Driver> bestDriver = new ArrayList<>();
		for(Driver d : allDriver) {
			if(d.getRating()>=4.5) {
				bestDriver.add(d);
			}
		}
		Collections.sort(bestDriver,(a,b)->Float.compare(b.getRating(), a.getRating()));
		if(bestDriver.isEmpty()) {
			throw new DriverException("No Best Driver with high Ratings");
		}
		else {
		   return bestDriver;
		}
	}


	@Override
	public Driver viewDriver(String LicenseNo) throws DriverException {
		// TODO Auto-generated method stub
		Optional<Driver> existingDriver = dRepo.findByLicenseNo(LicenseNo);
		if(existingDriver.isPresent()) {
			return existingDriver.get();
		}
		else {
			throw new DriverException("Driver is not Registered");
		}
	}
	
	
	
	

	
	
}
