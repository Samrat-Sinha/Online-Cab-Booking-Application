package com.cab.Repositary;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cab.Model.Driver;


@Repository
public interface DriverRepo extends JpaRepository<Driver, Integer>{

    Optional<Driver> findByLicenseNo(String licenseNo);

    Optional<Driver> findFirstByCabCarTypeAndCurrentLocationAndOnAnotherRide(String carType, String fromLocation, boolean onAnotherRide);


	
}
