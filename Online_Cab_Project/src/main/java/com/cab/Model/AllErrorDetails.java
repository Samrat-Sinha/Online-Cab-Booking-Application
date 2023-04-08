package com.cab.Model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AllErrorDetails {

	private LocalDateTime timeStamp;
	private String message;
	private String Description;
	
}
