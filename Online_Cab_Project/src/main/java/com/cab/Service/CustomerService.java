package com.cab.Service;



import com.cab.Exception.CurrentUserSessionException;
import com.cab.Exception.UserException;
import com.cab.Model.User;

public interface CustomerService {

	public User insertCustomer(User user)throws UserException;
	
	public User updateCustomer(User user,String uuid)throws UserException,CurrentUserSessionException;
	
	public User deleteCustomer(String customerPhoneNumber,String uuid)throws UserException,CurrentUserSessionException;
	
	
	
	
}