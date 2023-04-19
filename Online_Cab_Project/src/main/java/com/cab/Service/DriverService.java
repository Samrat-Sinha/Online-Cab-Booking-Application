package com.cab.Service;

import java.util.List;

import com.cab.Exception.CurrentUserSessionException;
import com.cab.Exception.DriverException;
import com.cab.Model.Driver;

public interface DriverService {

    Driver insertDriver(Driver driver)throws DriverException;
	
	Driver updateDriver(Driver driver,String uuid)throws DriverException,CurrentUserSessionException;
	
	Driver deleteDriver(Integer driverId, String uuid)throws DriverException,CurrentUserSessionException;
	
	List<Driver> viewBestDriver(String uuid)throws DriverException,CurrentUserSessionException;
	
	Driver viewDriver(Integer driverId,String uuid)throws DriverException,CurrentUserSessionException;
	
}
