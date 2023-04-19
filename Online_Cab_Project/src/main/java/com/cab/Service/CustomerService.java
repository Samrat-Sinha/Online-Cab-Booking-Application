package com.cab.Service;



import java.util.List;

import com.cab.Exception.CurrentUserSessionException;
import com.cab.Exception.CustomerException;
import com.cab.Model.Customer;

public interface CustomerService {

	Customer insertCustomer(Customer customer) throws CustomerException;
	
	Customer updateCustomer(Customer customer,String uuid) throws CustomerException, CurrentUserSessionException;
	
	Customer deleteCustomer(Integer customerId,String uuid)  throws CustomerException, CurrentUserSessionException;
	
	List<Customer> viewCustomer(String uuid)  throws CustomerException, CurrentUserSessionException;
	
	Customer viewCustomer(Integer customerId, String uuid) throws CustomerException, CurrentUserSessionException;
	

	
	
}
