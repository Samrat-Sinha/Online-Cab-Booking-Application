package com.cab.Service;

import java.util.List;

import com.cab.Exception.DriverException;
import com.cab.Model.Driver;

public interface DriverService {

	public Driver insertDriver(Driver driver)throws DriverException;
	
	public Driver updateDriver(Driver driver)throws DriverException;
	
	public Driver deleteDriver(String LicenseNo)throws DriverException;
	
	public List<Driver> viewBestDrivers()throws DriverException;
	
	public Driver viewDriver(String LicenseNo)throws DriverException;
}
