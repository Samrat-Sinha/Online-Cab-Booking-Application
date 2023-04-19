package com.cab.Repositary;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cab.Model.Cab;

public interface CabRepo extends JpaRepository<Cab, Integer>{

	Optional<Cab> findByCarNumber(String carNumber);
}
