package com.cab.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cab.Exception.DriverException;
import com.cab.Model.Cab;
import com.cab.Model.Driver;
import com.cab.Repositary.CabRepo;
import com.cab.Repositary.DriverRepo;
@Service
public class DriverServiceImpl implements DriverService{

	@Autowired
	private DriverRepo dRepo; 
	
	@Autowired
    private CabRepo cabRepo;
	
	@Override
	public Driver insertDriver(Driver driver) throws DriverException {
	    Optional<Driver> existingDriver = dRepo.findByLicenseNo(driver.getLicenseNo());
	    if (existingDriver.isPresent()) {
	        
	        throw new DriverException("Driver is Already Registered");
	        
	    } else {

	        
	        return dRepo.save(driver);
	    }
	}

	
	
}
