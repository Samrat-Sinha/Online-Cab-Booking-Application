package com.cab.Service;

import java.util.List;

import com.cab.Exception.UserException;
import com.cab.Model.CurrentUserSession;
import com.cab.Model.LoginDTO;
import com.cab.Model.User;

public interface CustomerService {

	public User insertCustomer(User user)throws UserException;
	
	public User updateCustomer(User user)throws UserException;
	
	public User deleteCustomer(String customerPhoneNumber)throws UserException;
	
	public List<User> viewCustomers()throws UserException;
	
	public User viewCustomer(String customerPhoneNumber)throws UserException;
	
	
}
