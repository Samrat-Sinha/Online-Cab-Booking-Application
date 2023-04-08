package com.cab.Exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.cab.Model.AllErrorDetails;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UserException.class)
	public ResponseEntity<AllErrorDetails> userErr(UserException ue, WebRequest req){
		AllErrorDetails err = new AllErrorDetails();
		err.setTimeStamp(LocalDateTime.now());
		err.setMessage(ue.getMessage());
		err.setDescription(req.getDescription(false));
		return new ResponseEntity<AllErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(TripBookingException.class)
	public ResponseEntity<AllErrorDetails> tripBookingErr(TripBookingException tbe, WebRequest req){
		AllErrorDetails err = new AllErrorDetails();
		err.setTimeStamp(LocalDateTime.now());
		err.setMessage(tbe.getMessage());
		err.setDescription(req.getDescription(false));
		return new ResponseEntity<AllErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(DriverException.class)
	public ResponseEntity<AllErrorDetails> driverErr(DriverException de, WebRequest req){
		AllErrorDetails err = new AllErrorDetails();
		err.setTimeStamp(LocalDateTime.now());
		err.setMessage(de.getMessage());
		err.setDescription(req.getDescription(false));
		return new ResponseEntity<AllErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CabException.class)
	public ResponseEntity<AllErrorDetails> cabErr(CabException ce, WebRequest req){
		AllErrorDetails err = new AllErrorDetails();
		err.setTimeStamp(LocalDateTime.now());
		err.setMessage(ce.getMessage());
		err.setDescription(req.getDescription(false));
		return new ResponseEntity<AllErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<AllErrorDetails> Err(Exception ee, WebRequest req){
		AllErrorDetails err = new AllErrorDetails();
		err.setTimeStamp(LocalDateTime.now());
		err.setMessage(ee.getMessage());
		err.setDescription(req.getDescription(false));
		return new ResponseEntity<AllErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
}
