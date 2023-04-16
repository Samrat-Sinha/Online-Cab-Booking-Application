package com.cab.Service;

import java.util.List;

import com.cab.Exception.CabException;
import com.cab.Exception.DriverException;
import com.cab.Model.Cab;

public interface CabService {

	public Cab updateCab(String LicenseNo ,Cab cab)throws DriverException;
	
	public List<Cab> viewCabsOfType(String carType)throws CabException; 
	
	public Integer countCabsOfType(String carType)throws CabException;
	
}
