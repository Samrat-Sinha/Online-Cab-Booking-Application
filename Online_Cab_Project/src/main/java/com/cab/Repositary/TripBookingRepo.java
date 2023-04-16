package com.cab.Repositary;




import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cab.Model.TripBooking;
import com.cab.Model.User;

@Repository
public interface TripBookingRepo extends JpaRepository<TripBooking, Integer>{

	List<TripBooking> findByFromDateTimeBetweenAndUser(LocalDateTime fromDateTime, LocalDateTime toDateTime, User user);
	
}
