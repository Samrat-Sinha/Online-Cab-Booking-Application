package com.cab.Repositary;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cab.Model.CarType;
import com.cab.Model.CurrentUserSession;
import com.cab.Model.Driver;

@Repository
public interface DriverRepo extends JpaRepository<Driver, Integer>{

    Optional<Driver> findByLicenseNo(String licenseNo);

    List<Driver> findAllByOrderByRatingDesc();

    Optional<Driver> findFirstByCabCarTypeAndCurrentLocation(CarType carType, String currentLocation);
    

	
}
