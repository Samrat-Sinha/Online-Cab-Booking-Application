package com.cab.Repositary;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cab.Model.CurrentUserSession;

@Repository
public interface CurrentUserSessionRepo extends JpaRepository<CurrentUserSession , Integer>{

	public Optional<CurrentUserSession> findByUuid(String uuid);
}
