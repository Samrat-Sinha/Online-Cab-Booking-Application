package com.cab.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cab.Exception.CurrentUserSessionException;
import com.cab.Exception.CustomerException;
import com.cab.Model.CurrentUserSession;
import com.cab.Model.Customer;
import com.cab.Repositary.CurrentUserSessionRepo;
import com.cab.Repositary.CustomerRepo;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerRepo customerRepo;
	
	@Autowired
	private CurrentUserSessionRepo currRepo;
	
	@Override
	public Customer insertCustomer(Customer customer) throws CustomerException {

		Optional<Customer> cust = customerRepo.findByEmail(customer.getEmail());
		if(cust.isPresent()) {
			throw new CustomerException("Customer is Already Registered");
		}
		else {
			if(customer.getUserRole().equalsIgnoreCase("Customer")) {
				return customerRepo.save(customer);
			}
			else {
				throw new CustomerException("The User is not a Customer");
			}
		}
	}

	@Override
	public Customer updateCustomer(Customer customer, String uuid)
			throws CustomerException, CurrentUserSessionException {
		Optional<CurrentUserSession> validCustomer = currRepo.findByUuid(uuid);
		if(validCustomer.isPresent()) {
			Optional<Customer> cust = customerRepo.findByEmail(customer.getEmail());
			if(cust.isPresent()) {
				Customer updatingCustomer = cust.get();
				updatingCustomer.setAddress(customer.getAddress());
				updatingCustomer.setEmail(customer.getEmail());
				updatingCustomer.setMobileNumber(customer.getMobileNumber());
				updatingCustomer.setPassword(customer.getPassword());
				updatingCustomer.setUserName(customer.getUserName());
				 
				 return customerRepo.save(updatingCustomer);
			}
			else {
				throw new CustomerException("Customer not found with this Credentials");
			}
		}
		else {
			throw new CurrentUserSessionException("Customer is Not Logged In");
		}
	}

	@Override
	public Customer deleteCustomer(Integer customerId, String uuid)
			throws CustomerException, CurrentUserSessionException {
		Optional<CurrentUserSession> validCustomer = currRepo.findByUuidAndRole(uuid);
		if(validCustomer.isPresent()) {
			Optional<Customer> cust = customerRepo.findById(customerId);
			if(cust.isPresent()) {
				Customer customer = cust.get();
				customerRepo.delete(customer);
				 return customer;
			}
			else {
				throw new CustomerException("Customer not found with this details");
			}
		}
		else {
			throw new CurrentUserSessionException("Customer is Not Logged In");
		}
	}

	@Override
	public List<Customer> viewCustomer(String uuid) throws CustomerException, CurrentUserSessionException {
		// TODO Auto-generated method stub
		Optional<CurrentUserSession> validCustomer = currRepo.findByUuidAndRole(uuid);
		if(validCustomer.isPresent()) {
			List<Customer> viewCustomers = customerRepo.findAll();
			if(!viewCustomers.isEmpty()) {
				return viewCustomers;
			}
			else {
				throw new CustomerException("No Customer present");
			}
		}
		else {
			throw new CurrentUserSessionException("Customer is Not Logged In");
		}
	}

	@Override
	public Customer viewCustomer(Integer customerId, String uuid)
			throws CustomerException, CurrentUserSessionException {
		// TODO Auto-generated method stub
		Optional<CurrentUserSession> validCustomer = currRepo.findByUuidAndRole(uuid);
		if(validCustomer.isPresent()) {
			Optional<Customer> cust = customerRepo.findById(customerId);
			if(cust.isPresent()) {
				 return cust.get();
			}
			else {
				throw new CustomerException("Customer not found with this details");
			}
		}
		else {
			throw new CurrentUserSessionException("Customer is Not Logged In");
		}
	}

	

	

}
