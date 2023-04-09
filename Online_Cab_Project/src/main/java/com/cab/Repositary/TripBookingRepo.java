package com.cab.Repositary;



import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cab.Model.TripBooking;

@Repository
public interface TripBookingRepo extends JpaRepository<TripBooking, Integer>{

	@Query("SELECT COUNT(t) > 0 FROM TripBooking t WHERE t.fromDateTime = :fromDateTime AND t.toDateTime = :toDateTime AND t.fromLocation = :fromLocation AND t.toLocation = :toLocation")
    boolean existsByDateTimeAndLocation(LocalDateTime fromDateTime, LocalDateTime toDateTime, String fromLocation, String toLocation);
}
