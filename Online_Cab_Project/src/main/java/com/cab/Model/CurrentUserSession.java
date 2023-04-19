package com.cab.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CurrentUserSession {

	@Id
	private Integer currUserId;
	private String uuid;
	private String currRole;
	private String currStatus;
}
