package com.cab.Model;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class User {

	private String userName;
	private String password;
	private String address;
	private String mobileNumber;
	private String email;
	private String userRole;
	
}
