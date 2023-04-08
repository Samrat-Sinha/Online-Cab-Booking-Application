package com.cab.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cab.Model.Driver;

@Repository
public interface DriverRepo extends JpaRepository<Driver, Integer>{

}
