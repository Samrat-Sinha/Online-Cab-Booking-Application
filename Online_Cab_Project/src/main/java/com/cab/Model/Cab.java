package com.cab.Model;




import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cab {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer cabId;
	
	@Enumerated(EnumType.STRING)
	private CarType carType;
	
	private String carName;
	private String carNumber;
	private float perKmRate;
	
}