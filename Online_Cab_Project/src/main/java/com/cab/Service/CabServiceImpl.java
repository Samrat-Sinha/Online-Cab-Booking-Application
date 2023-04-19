package com.cab.Service;

import java.lang.StackWalker.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cab.Exception.CabException;
import com.cab.Exception.CurrentUserSessionException;
import com.cab.Model.Cab;
import com.cab.Model.CurrentUserSession;
import com.cab.Repositary.CabRepo;
import com.cab.Repositary.CurrentUserSessionRepo;

@Service
public class CabServiceImpl implements CabService{

	@Autowired
	private CabRepo cabRepo;
	
	@Autowired
	private CurrentUserSessionRepo currRepo;
	
	@Override
	public Cab insertCab(Cab cab) throws CabException {
		
		Optional<Cab> findCab = cabRepo.findByCarNumber(cab.getCarNumber());
		if(findCab.isEmpty()) {
			return cabRepo.save(cab);
		}
		else {
			throw new CabException("Cab is already Registered");
		}
	}

	@Override
	public Cab updateCab(Cab cab, String uuid) throws CabException, CurrentUserSessionException {
		
		Optional<CurrentUserSession> validuser = currRepo.findByUuidAndRole(uuid);
		if(validuser.isPresent()) {
			Optional<Cab> cb = cabRepo.findByCarNumber(cab.getCarNumber());
			if(cb.isPresent()) {
				
				Cab data = cb.get();
				data.setCarName(cab.getCarName());
				data.setCarNumber(cab.getCarNumber());
				data.setCarType(cab.getCarType());
				data.setPerKmRate(cab.getPerKmRate());
				
				return cabRepo.save(data);
				
			}
			else {
				throw new CabException("Cab is not Registered");
			}
		}
		else {
			throw new CurrentUserSessionException("User not login In or User is not an Admin");
		}
	}

	@Override
	public Cab deleteCab(Integer cabId, String uuid) throws CabException, CurrentUserSessionException {
		
		Optional<CurrentUserSession> validuser = currRepo.findByUuidAndRole(uuid);
		if(validuser.isPresent()) {
			Optional<Cab> cb = cabRepo.findById(cabId);
			if(cb.isPresent()) {
				Cab cab = cb.get();
				cabRepo.delete(cab);
				return cab;
			}
			else {
				throw new CabException("Cab is not Registered");
			}
		}
		else {
			throw new CurrentUserSessionException("User not login In or User is not an Admin");
		}
	}

	@Override
	public List<Cab> viewCabsOfType(String carType, String uuid) throws CabException, CurrentUserSessionException {
		
		Optional<CurrentUserSession> validuser = currRepo.findByUuidAndRole(uuid);
		if(validuser.isPresent()) {
			List<Cab> allCabs = cabRepo.findAll();
			List<Cab> viewCabsOfType = new ArrayList<>();

			for(Cab cab : allCabs) {
				if(cab.getCarType().equalsIgnoreCase(carType)) {
					viewCabsOfType.add(cab);
				}
			}
			if(viewCabsOfType.isEmpty()) {
				throw new CabException("No Cab is Registered");
			}
			else {
				return viewCabsOfType;
			}
		}
		else {
			throw new CurrentUserSessionException("User not login In or User is not an Admin");
		}
	}

	@Override
	public Integer countCabsOfType(String carType, String uuid) throws CabException, CurrentUserSessionException {
		Optional<CurrentUserSession> validuser = currRepo.findByUuidAndRole(uuid);
		if(validuser.isPresent()) {
			List<Cab> allCabs = cabRepo.findAll();
			List<Cab> viewCabsOfType = new ArrayList<>();
			Integer countCabsOfType = 0;

			for(Cab cab : allCabs) {
				if(cab.getCarType().equalsIgnoreCase(carType)) {
					countCabsOfType++;
				}
			}
			if(viewCabsOfType.isEmpty()) {
				throw new CabException("No Cab found with the given type");
			}
			else {
				return countCabsOfType;
			}
		}
		else {
			throw new CurrentUserSessionException("User not login In or User is not an Admin");
		}
		
	}

}
