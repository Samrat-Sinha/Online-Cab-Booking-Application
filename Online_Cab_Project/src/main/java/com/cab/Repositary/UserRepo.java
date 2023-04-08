package com.cab.Repositary;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cab.Model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>{
  
	public Optional<User> findByMobileNumber(String mobileNumber);

	public Optional<User> findByEmail(String email);
}
