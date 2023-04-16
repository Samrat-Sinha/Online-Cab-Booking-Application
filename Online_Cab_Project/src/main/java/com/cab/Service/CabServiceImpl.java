package com.cab.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cab.Exception.CabException;
import com.cab.Exception.DriverException;
import com.cab.Model.Cab;
import com.cab.Model.Driver;
import com.cab.Repositary.CabRepo;
import com.cab.Repositary.DriverRepo;
@Service
public class CabServiceImpl implements CabService{

	@Autowired
	private DriverRepo dRepo;
	
	@Autowired
	private CabRepo cabRepo;
	
	@Override
	public Cab updateCab(String LicenseNo ,Cab cab) throws DriverException {
		// TODO Auto-generated method stub
		Optional<Driver> drv = dRepo.findByLicenseNo(LicenseNo);
		if(drv.isPresent()) {
			Driver driver = drv.get();
			Cab oldCab = driver.getCab();
			driver.setCab(cab);
			cabRepo.delete(oldCab);
			dRepo.save(driver);
			cabRepo.save(cab);
			return cab;
		}
		else {
			throw new DriverException("Driver is not Present");
		}
	}

	@Override
	public List<Cab> viewCabsOfType(String carType) throws CabException {
		// TODO Auto-generated method stub
		List<Cab> allcab = cabRepo.findAll();
		List<Cab> ans = new ArrayList<>();
		for(Cab cab : allcab) {
			if(cab.getCarType().equals(carType)) {
				ans.add(cab);
			}
		}
		if(ans.isEmpty()) {
			throw new CabException("No Cab Found of the CarType");
		}
		else {
			return ans;
		}
	}

	@Override
	public Integer countCabsOfType(String carType) throws CabException {
		// TODO Auto-generated method stub
		List<Cab> allcab = cabRepo.findAll();
		Integer count = 0;
		for(Cab cab : allcab) {
			if(cab.getCarType().equals(carType)) {
				count++;
			}
		}
		if(count==0) {
			throw new CabException("No Cab Found of the CarType");
		}
		else {
			return count;
		}
	}

}
